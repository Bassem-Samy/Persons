package com.bassem.persons.database.models;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Person {

    public Person(String id, String name, String imageUrl, String phones, String emails) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.id = id;
        this.phones = phones;
        this.emails = emails;
    }

    private String id;
    private String name;
    private String imageUrl;
    private String phones;
    private String emails;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
