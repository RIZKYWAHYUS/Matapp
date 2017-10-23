package com.example.user.matapp;

import com.google.firebase.database.IgnoreExtraProperties;
 
/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */
 
@IgnoreExtraProperties
public class User {
 
    public String nama;
    public String email;
    public String nomorTelp;
    public String jenisKelamin;
    public String umur;
 
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }
 
    public User(String nama, String email, String no, String jk, String umur){
        this.nama = nama;
        this.email = email;
        this.nomorTelp = no;
        this.jenisKelamin = jk;
        this.umur = umur;
    }

}