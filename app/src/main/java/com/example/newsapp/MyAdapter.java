package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

// adapter 的主要功能有
// 根据后端要求改变前端视图参数
public class MyAdapter extends BaseAdapter {
    private ArrayList<NewsItem> array;
    private Context context;

    public MyAdapter(ArrayList<NewsItem> array, Context context){
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount(){
        return array.size();
    }

    @Override
    public Object getItem(int position){
        //return array.get(position);
        return null;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    // get view 函数作用是 获取当前Item的视图
    // position 是 View 在 View Group 的位置
    // (View) convertView 是被移出屏幕但并没有被 View 回收的 Item
    // (viewGroup) parent 是父容器
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        // 若是 convertView 为空，代表界面未满，则需要新建视图
        // 若是 convertView 非空，则代表界面已满，此时出现新视图时，不需新建，只要复用已消失的视图即可
        // tag 指 ViewHolder 实例化的一个属性
        // 在实例化后，convertView 会因为 java 的引用机制而一直存活
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_txt);
            viewHolder.timeView = (TextView) convertView.findViewById(R.id.tv_item_date);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            viewHolder.textView.setText(array.get(position).get_title());
            String time = array.get(position).get_time();
            if(!time.equals(""))
                viewHolder.timeView.setText(time);
        }catch(Exception e){
            throw e;
        }
        return convertView;
    }


    private class ViewHolder{
        TextView textView;
        TextView timeView;
    }
}
