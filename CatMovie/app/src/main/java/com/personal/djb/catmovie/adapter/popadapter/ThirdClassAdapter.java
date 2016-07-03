package com.personal.djb.catmovie.adapter.popadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.cinema.cinemamenu.ThirdClassItem;

import java.util.List;

/**
 * 二级分类（即右侧菜单）的adapter
 * Created by hanj on 14-9-25.
 */
public class ThirdClassAdapter extends BaseAdapter{
    private Context context;
    private List<ThirdClassItem> list;

    public ThirdClassAdapter(Context context, List<ThirdClassItem> list){
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
            holder.nameTV = (TextView) convertView.findViewById(R.id.right_item_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTV.setText(list.get(position).getName());

        return convertView;
    }

    private class ViewHolder{
        TextView nameTV;
    }

}
