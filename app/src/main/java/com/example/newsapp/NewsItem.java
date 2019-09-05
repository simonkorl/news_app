package com.example.newsapp;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private String title;
    private String author;
    private String link;
    private String content;
    private String time;

    public NewsItem(String title, String author, String link, String content, String time){
        this.title = title;
        this.author = author;
        this.link = link;
        this.content = content;
        this.time = time;
    }

    public void init(String title, String author, String link, String content, String time){
        this.title = title;
        this.author = author;
        this.link = link;
        this.content = content;
        this.time = time;
    }

    public String get_title(){
        return title;
    }

    public String get_content(){
        return content;
    }

    public String get_writer(){
        return author;
    }

    public String get_time(){
        return time;
    }

    public String getAuthor(){ return author; }

    public String getLink() { return link; }
}
