package com.bassem.persons.models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Company {
    @SerializedName("info")
    private CompanyInfo info;

    public CompanyInfo getInfo() {
        return info;
    }

    public void setInfo(CompanyInfo info) {
        this.info = info;
    }
}
