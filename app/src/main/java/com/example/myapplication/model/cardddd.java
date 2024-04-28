package com.example.myapplication.model;

public class cardddd {

    String uid,type,title,cardholdname,number,branch,month,year,cvv;
    public cardddd(String uid, String type, String title, String cardholdname, String number, String branch, String month, String year, String cvv)
    {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.cardholdname = cardholdname;
        this.number = number;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
    }

    public cardddd() {}

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

    public String getCardholdname() {
        return cardholdname;
    }

    public void setCardholdname(String cardholdname) {
        this.cardholdname = cardholdname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
