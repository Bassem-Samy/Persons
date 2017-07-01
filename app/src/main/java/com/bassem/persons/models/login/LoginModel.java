package com.bassem.persons.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class LoginModel {
    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
