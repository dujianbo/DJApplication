package com.personal.djb.catmovie.adapter.popadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.cinema.cinemamenu.SecondClassItem;

import java.util.List;

/**
 * 二级分类（即右侧菜单）的adapter
 * Created by hanj on 14-9-25.
 */
public class SecondClassAdapter extends BaseAdapter{
    private Context context;
    private List<SecondClassItem> list;

    public SecondClassAdapter(Context context, List<SecondClassItem> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.right_listview_item, null);
//            convertView = View.inflate(context,R.layout.right_listview_item,null);
            holder.nameTV = (TextView) convertView.findViewById(R.id.right_item_name);
//            holder.nameTV = (TextView) convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //选中和没选中时，设置不同的颜色
        if (position == selectedPosition){
            holder.nameTV.setEnabled(true);
            convertView.setBackgroundResource(R.color.popup_right_bg);
        }else{
            holder.nameTV.setEnabled(false);
            convertView.setBackgroundResource(R.drawable.selector_left_normal);
        }

        holder.nameTV.setText(list.get(position).getName());

        return convertView;
    }

    private class ViewHolder{
        TextView nameTV;
    }

    private int selectedPosition = 0;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
