package com.example.foodieapp;


public class Items {
    String image;
    String title;
    String topic;
    String hv[] = new String[2];
    String cv[] = new String[2];
    String c[] = new String[2];
    String cl[] = new String[2];
    String s[] = new String[2];


    public Items(String image, String title, String description, String[] hv, String[] cv, String[] c, String[] cl, String[]s) {
        this.image = image;
        this.title = title;
        this.topic = description;
        this.hv = hv;
        this.cv = cv;
        this.c = c;
        this.cl = cl;
        this.s = s;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String description) {
        this.topic = description;
    }

    public String[] getHv() {
        return this.hv;
    }

    public void setHv(String[] a) {
        this.hv = a;
    }

    public String[] getCv() {
        return this.cv;
    }

    public void setCv(String[] a) {
        this.cv = a;
    }

    public String[] getC() {
        return this.c;
    }

    public void setC(String[] a) {
        this.c = a;
    }

    public String[] getCl() {
        return this.cl;
    }

    public void setCl(String[] a) {
        this.cl = a;
    }

    public String[] getS() {
        return this.s;
    }

    public void setS(String[] a) {
        this.s = a;
    }

}
