package com.buzzware.apnigaridriver.activities.auth.vm.mo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    public Boolean isOnline;
    public Boolean isActive;
    public String id;
    public String clientSecret;
    public String firstName;
    public String lastName;
    public String password;
    public String homeAddress;
    public String workAddress;
    public String phoneNumber;
    public String state;

    @SerializedName("stripeCustid")
    public String stripeCustid;

    public String city;

    public String token;

    public ArrayList<Double> ratings;

    public double lat;

    public double lng;

    public double balance=0.0;

    public String userRole = "user";

    public String zipcode;

    public String email;

    public String image;
}
