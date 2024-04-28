package com.example.myapplication.model;


public class noteee {

    String uid,type,title,content;
    public noteee(String uid, String type, String title, String content)
    {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public noteee() {}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
