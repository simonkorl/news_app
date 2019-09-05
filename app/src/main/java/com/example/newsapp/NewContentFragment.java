package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NewContentFragment extends Fragment{
    NewContentFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content, container, false);
        TextView title = (TextView) view.findViewById(R.id.news_title);
        title.setText(getArguments().getString("title"));

        String news_time = getArguments().getString("time");
        String news_writer = getArguments().getString("writer");
        if(!news_time.equals(""))
        {
            TextView time = (TextView) view.findViewById(R.id.news_time);
            time.setText(news_time);
        }
        if(!news_writer.equals(""))
        {
            TextView writer = (TextView) view.findViewById(R.id.news_writer);
            writer.setText(news_writer);
        }
        TextView content = (TextView) view.findViewById(R.id.news_content);
        content.setText(getArguments().getString("content"));
        return view;
    }
}
