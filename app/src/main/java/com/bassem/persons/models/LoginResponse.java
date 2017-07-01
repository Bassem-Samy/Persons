package com.bassem.persons.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public class LoginResponse {
    @SerializedName("data")
    private List<LoginData> data;
    @SerializedName("success")
    private boolean success;

    public List<LoginData> getData() {
        return data;
    }

    public void setData(List<LoginData> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
