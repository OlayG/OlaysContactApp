package com.example.admin.olayscontactapp;

/**
 * Created by Admin on 8/8/2017.
 */

public class AdapterItems {

    public String FullName;
    public String PhoneNumber;
    public byte[] Photo;

    public AdapterItems(String fullName, String phoneNumber, byte[] photo) {
        this.FullName = fullName;
        this.PhoneNumber = phoneNumber;
        this.Photo = photo;
    }
}
