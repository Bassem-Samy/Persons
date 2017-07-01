package com.bassem.persons.database.models;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Person {

    public Person(String id, String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    private String id;
    private String name;
    private String imageUrl;

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
}
