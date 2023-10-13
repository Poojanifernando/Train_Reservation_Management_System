package com.example.reservationmanagement;


import com.google.gson.annotations.SerializedName;

public class UserRegistration {
    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String Name;

    @SerializedName("nic")
    private String NIC;

    @SerializedName("email")
    private String Email;

    @SerializedName("password")
    private String Password;

    @SerializedName("retypepassword")
    private String RetypePassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String nic) {
        this.NIC = nic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getRetypePassword() {
        return RetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.RetypePassword = retypePassword;
    }
}
