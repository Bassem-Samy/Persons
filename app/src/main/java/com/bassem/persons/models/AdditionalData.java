package com.bassem.persons.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class AdditionalData {
    @SerializedName("pagination")
    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
