package com.example.myapplication.model;


public class passworddd {

    String uid,type,title,username,url;
    String password;
    public passworddd(String uid, String type, String title, String username, String password, String url)
    {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public passworddd() {}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
