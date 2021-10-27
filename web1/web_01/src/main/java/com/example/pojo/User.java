package com.example.pojo;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-08 09:43:34
 */

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String gender;
    private String[] hobby;

    public User(String username, String password, String gender, String[] hobby) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobby = hobby;
    }

    public User() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }
}
