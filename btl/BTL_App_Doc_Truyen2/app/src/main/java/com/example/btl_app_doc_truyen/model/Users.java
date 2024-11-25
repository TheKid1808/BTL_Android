package com.example.btl_app_doc_truyen.model;

public class Users {
    private int UserID;
    private String UserName;
    private String Email;
    private String Password;
    //Đăng ký tài khoản mới
    public Users(String userName, String email, String password){
        this.UserName = userName;
        this.Email = email;
        this.Password = password;
    }
    //Lấy thông tin đăng nhập
    public Users (int userID, String userName, String email, String password){
        this.UserID = userID;
        this.UserName = userName;
        this.Email = email;
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
