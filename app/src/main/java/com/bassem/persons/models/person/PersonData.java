package com.bassem.persons.models.person;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonData {
    @SerializedName("org_name")
    private String orgName;
    @SerializedName("id")
    private String id;
    @SerializedName("companyId")
    private String companyId;
    @SerializedName("phone")
    private List<Phone> phones;
    @SerializedName("email")
    private List<Email> emails;
    @SerializedName("name")
    private String name;
    @SerializedName("picture_id")
    private PersonPictures pictures;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonPictures getPictures() {
        return pictures;
    }

    public void setPictures(PersonPictures pictures) {
        this.pictures = pictures;
    }

}
