package com.bassem.persons.models.person;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonPictures {
    @SerializedName("item_id")
    private String itemId;

    private String value;

    private Picture pictures;


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Picture getPictures() {
        return pictures;
    }

    public void setPictures(Picture pictures) {
        this.pictures = pictures;
    }

}
