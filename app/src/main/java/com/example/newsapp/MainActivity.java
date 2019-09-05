package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 标题栏
    private TextView title;
    // 新闻详情栏
    private FrameLayout content;
    // 菜单栏，用来监听用户选择
    private RadioGroup radioGroup;
    // 上下文
    // 可以理解为对场景的抽象
    private Context context;
    // 存储数据
    // to be continued
    private ArrayList<NewsItem> array = null;
    private ArrayList<NewsItem> suggest_array = null;
    // Fragment Manager，用来获取 Fragment
    private FragmentManager manager = null;
    // 上一次点击退出键的时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        // 动态获取 Fragment
        manager = getSupportFragmentManager();
        // title 更新为标题栏
        // content 更新为新闻详情栏
        // radioGroup 更新为下方菜单栏
        bindViews();
        // 开启对下方菜单的监听
        setListener();

        array = new ArrayList<NewsItem>();
        suggest_array = new ArrayList<NewsItem>();
        init_news();



        // 生成展示新闻的 Fragment
        NewsListFragment fragment = new NewsListFragment(manager, array);
        // FragmentTransaction 用作 Fragment 之间通信
        FragmentTransaction transaction = manager.beginTransaction();
        // 在首页中显示新闻详情栏
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    //
    private void bindViews(){
       title = (TextView) findViewById(R.id.app_title);
       content = (FrameLayout) findViewById(R.id.content);
       radioGroup = (RadioGroup) findViewById(R.id.rg_group);
    }

    // 初始化新闻
    // to be continued
    private void init_news(){
        for(int i = 0; i < 20; i++){
            NewsItem newsItem = new NewsItem(DemoData.demo_title[i], "xiaoming", "news.com", DemoData.demo_news, "");
            array.add(newsItem);
            NewsItem newsSuggItem = new NewsItem(DemoData.demo_title[i], "xiaoming", "news.com", "asdfghjkl", "2019年8月10日");
            suggest_array.add(newsSuggItem);
        }
    }

    // 设置对菜单的监听
    private void setListener()
    {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        FragmentTransaction transaction_home = manager.beginTransaction();
                        NewsListFragment fragment_home = new NewsListFragment(manager, array);
                        transaction_home.replace(R.id.content, fragment_home);
                        transaction_home.commit();
                        break;
                    case R.id.rb_category:
                        FragmentTransaction transaction_category = manager.beginTransaction();
                        NewsCatgoryFragment fragment_category = new NewsCatgoryFragment();
                        transaction_category.replace(R.id.content, fragment_category);
                        transaction_category.commit();
                        break;
                    case R.id.rb_ad:
                        FragmentTransaction transaction_ad = manager.beginTransaction();
                        NewsListFragment fragment_ad = new NewsListFragment(manager, suggest_array);
                        transaction_ad.replace(R.id.content, fragment_ad);
                        transaction_ad.commit();
                        break;
                    case R.id.rb_setting:
                        FragmentTransaction transaction_setting = manager.beginTransaction();
                        UserCatagory fragment_setting = new UserCatagory();
                        transaction_setting.replace(R.id.content, fragment_setting);
                        transaction_setting.commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    // 对回退键的处理
    // 若栈空，则退出
    // 否则弹出栈
    @Override
    public void onBackPressed(){
        // 得到回退栈中 Fragment 的数量
        if(manager.getBackStackEntryCount() == 0){
            // 栈空时退出即为完全退出
            // 此时需要在2s内连续按两次退出键
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                super.onBackPressed();
            }
        }else{
            // 返回上一个 Fragment
            manager.popBackStack();
            title.setText("新闻列表");
        }
    }
}
