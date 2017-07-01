package com.bassem.persons.models.person;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Picture {
    @SerializedName("512")
    private String url512;
    @SerializedName("128")
    private String url128;

    public String getUrl512() {
        return url512;
    }

    public void setUrl512(String url512) {
        this.url512 = url512;
    }

    public String geturl128() {
        return url128;
    }

    public void set128(String url128) {
        this.url128 = url128;
    }
}
