package com.example.cyclestationsemal.Admin.user;

public class User {

    public String uid;
    public String name;
    public String emailId;
    long phone;
    public String gender;
    public String address;
    public String password;

    User(){

    }

    public User(String uid, String name, String emailId, long phone, String gender, String address, String password) {
        this.uid = uid;
        this.name = name;
        this.emailId = emailId;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.password = password;
    }

    public String toString(){
        return "NAME : "+this.name+" , E-MAIL : "+this.emailId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
