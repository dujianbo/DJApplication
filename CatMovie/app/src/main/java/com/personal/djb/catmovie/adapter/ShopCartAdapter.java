package com.personal.djb.catmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.ShoppingCart;
import com.personal.djb.catmovie.utils.CartProvider;
import com.personal.djb.catmovie.view.NumberAddSubView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {

    private final Context context;
    private final List<ShoppingCart> datas;
    private final CheckBox checkbox_all;
    private final TextView tv_total_price;

    private CartProvider cartProvider;

    public ShopCartAdapter(Context context, final List<ShoppingCart> datas, final CheckBox checkbox_all, TextView tv_total_price) {
        this.context = context;
        this.datas = datas;
        this.checkbox_all = checkbox_all;
        this.tv_total_price = tv_total_price;
        cartProvider = new CartProvider(context);
        showTotalPrice();
        checkAll_none();
        //设置点击某条的监听
        setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //1.把该条对象的选中状态取反，刷新状态
                ShoppingCart cart = datas.get(position);
                cart.setIsCheck(!cart.isCheck());
                notifyItemChanged(position);//指定更新位置

                //2.校验全选按钮的状态
                checkAll_none();

                //3.显示总价格
                showTotalPrice();


            }
        });

        //设置CheckBox的点击事件
        checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1. 得到状态，如果是选中，就把所有数据设置勾选；如果状态是非勾选，设置全部为不选择
                all_none(checkbox_all.isChecked());

                //2.显示总价格
                showTotalPrice();
            }
        });

    }

    /**
     * 全选和非全选
     * @param ischeck
     */
    public void all_none(boolean ischeck) {
        if(datas != null && datas.size() >0){
            for(int i=0;i<datas.size();i++){
                ShoppingCart cart = datas.get(i);
                cart.setIsCheck(ischeck);
                notifyItemChanged(i);
            }

            if(datas.size()>0){
                checkbox_all.setVisibility(View.VISIBLE);
            }else{
                checkbox_all.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 全选和非全选
     */
    public void checkAll_none() {
        if(datas != null && datas.size() >0){

            int number = 0;

            for (int i=0;i<datas.size();i++){

                ShoppingCart cart = datas.get(i);
                if(!cart.isCheck()){//只要有一个不被选中，就设置全选为非勾选状态
                    //没选择的
                    checkbox_all.setChecked(false);
                }else{
                    //选中的
                    number += 1;
                }
            }

            if(number ==datas.size()){
                checkbox_all.setChecked(true);
            }

            if(datas.size()>0){
                checkbox_all.setVisibility(View.VISIBLE);
            }else{
                checkbox_all.setVisibility(View.GONE);
            }

        }
    }

    /**
     * 显示总价格
     */
    public void showTotalPrice() {
        //2.设置到tv_total_price
        if(tv_total_price != null)
            tv_total_price.setText("合计￥"+getTotalPrice()*100/100.00);
    }

    /**
     * 得到总价格
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {

                ShoppingCart cart = datas.get(i);
                //只是计算勾选的产品
                if (cart.isCheck()) {
                    totalPrice = totalPrice + cart.getCount() * cart.getPrice();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(context, R.layout.item_shop_cart, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShoppingCart cart = datas.get(position);
        holder.checkbox.setChecked(cart.isCheck());
        holder.tv_name.setText(cart.getTitle());
        holder.tv_price.setText("￥" + cart.getPrice());
        holder.number_add_sub_view.setValue(cart.getCount());

        //请求图片
        Glide.with(context).load(cart.getPic())
                .placeholder(R.drawable.splash_2)
                .error(R.drawable.splash_2).
                into(holder.iv_icon);

        holder.number_add_sub_view.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onSubButton(View view, int value) {
                cart.setCount(value);
                cartProvider.update(cart);//把数据缓存到本地
                showTotalPrice();
            }

            @Override
            public void onAddButton(View view, int value) {
                cart.setCount(value);
                cartProvider.update(cart);//把数据缓存到本地
                showTotalPrice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 删除选中的数据，并且刷新
     */
    public void deleteData() {
        if(datas != null && datas.size() >0){

            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                ShoppingCart cart = (ShoppingCart) iterator.next();
                if(cart.isCheck()){
                    int position = datas.indexOf(cart);
                    iterator.remove();//移除数据
                    cartProvider.delete(cart);
                    notifyItemRemoved(position);//当移除的时候用这个刷新
                }
            }


            if(datas.size()>0){
                checkbox_all.setVisibility(View.VISIBLE);
            }else{
                checkbox_all.setVisibility(View.GONE);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox;
        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_price;
        private NumberAddSubView number_add_sub_view;

        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            number_add_sub_view = (NumberAddSubView) itemView.findViewById(R.id.number_add_sub_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null){
                        itemClickListener.onItemClick(v,getLayoutPosition());
                    }
                }
            });

        }
    }

    /**
     * 点击某一条的监听
     */
    public interface  OnItemClickListener{
        /**
         * 当某一条被点击的时候回调这个方法
         * @param view
         * @param position
         */
        public void onItemClick(View view, int position);
    }

    private  OnItemClickListener itemClickListener;

    /**
     * 设置点击某一条的监听
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
