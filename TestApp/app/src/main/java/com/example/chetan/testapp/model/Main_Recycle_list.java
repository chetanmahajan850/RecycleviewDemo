package com.example.chetan.testapp.model;

import java.io.Serializable;

public class Main_Recycle_list implements Serializable {

    private  String name,phone,profile;



    public Main_Recycle_list(String name, String phone, String profile){
        this.phone = phone;
        this.name = name;
        this.profile = profile;

    }


    public Main_Recycle_list() {
    }


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }
}

