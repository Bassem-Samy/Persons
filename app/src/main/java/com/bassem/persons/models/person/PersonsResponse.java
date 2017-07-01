package com.bassem.persons.models.person;

import com.bassem.persons.models.AdditionalData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonsResponse {

    @SerializedName("additional_data")
    private AdditionalData additionalData;
    @SerializedName("data")
    private List<PersonData> data;
    @SerializedName("success")
    private String success;

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    public List<PersonData> getData() {
        return data;
    }

    public void setData(List<PersonData> data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
