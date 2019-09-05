package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {
    // Fragment 管理器
    private FragmentManager manager;
    // 新闻列表
    private ArrayList<NewsItem> array;
    // 新闻简介列表
    private ListView news_list;

    NewsListFragment(FragmentManager manager, ArrayList<NewsItem> array){
        this.manager = manager;
        this.array = array;
    }

    // (LayoutInflater)inflater 布局加载器，将 xml 加载成 View 或者 ViewGroup
    // (ViewGroup) container，存放包裹 Fragment 的 view
    // (Bundle) savedInstanceState，保存 activity 状态
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // 加载布局，提取 view
        View view = inflater.inflate(R.layout.news_list, container, false);
        news_list = (ListView) view.findViewById(R.id.NewsList);
        // adapter 用来做前后端的交互
        MyAdapter adapter = new MyAdapter(array, getActivity());
        // list_view 提供了利用set adapter 来加载参数的方法
        news_list.setAdapter(adapter);
        // 监听鼠标点击操作
        news_list.setOnItemClickListener(this);
        return view;
    }

    // (AdapterView<?>) parent，用来识别是哪一个 list view
    // (View) view， 用来获取当前list view 的布局
    // position 获取当前 item 在 list view 中的位置
    // id 是当前 item 在 list view 第几行的位置
    // id 和 position 的区别是，id 考虑了 headView 和 footerView
    // 在本程序中，建议无视 id，使用 position
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        FragmentTransaction transaction = manager.beginTransaction();
        // new content fragment 是新闻详情页的 fragment
        // 这里 news 少写了一个 s
        NewContentFragment newContentFragment = new NewContentFragment();
        // bundle 是一个特殊的 map，他的键类型固定为 String
        Bundle bundle = new Bundle();
        // 这里获取了当前新闻的正文
        // to be continued
        String news_content = array.get(position).get_content();
        bundle.putString("content", news_content);
        bundle.putString("writer", array.get(position).get_writer());
        bundle.putString("time", array.get(position).get_time());
        bundle.putString("title", array.get(position).get_title());
        newContentFragment.setArguments(bundle);


        try {
            // Fragment 替换动画
            transaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
            //transaction.replace(R.id.main_frag, newContentFragment);
            transaction.replace(((ViewGroup)getView().getParent()).getId(), newContentFragment);

            TextView title = (TextView) getActivity().findViewById(R.id.app_title);
            title.setText("新闻详情");

            // Fragment压栈
            transaction.addToBackStack(null);
            // 应用 fragment 变化
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }
}
