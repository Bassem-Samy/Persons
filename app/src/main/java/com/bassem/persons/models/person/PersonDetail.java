package com.bassem.persons.models.person;

/**
 * Created by Bassem on 7/3/2017.
 */

public class PersonDetail {
    private String name;
    private String Id;
    private String emailsDisplayText;
    private String phonesDisplayText;
    private String imageUrl;

    public PersonDetail(String id, String name, String imageUrl, String phonesDisplayText, String emailsDisplayText) {
        this.Id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.emailsDisplayText = emailsDisplayText;
        this.phonesDisplayText = phonesDisplayText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmailsDisplayText() {
        return emailsDisplayText;
    }

    public void setEmailsDisplayText(String emailsDisplayText) {
        this.emailsDisplayText = emailsDisplayText;
    }

    public String getPhonesDisplayText() {
        return phonesDisplayText;
    }

    public void setPhonesDisplayText(String phonesDisplayText) {
        this.phonesDisplayText = phonesDisplayText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
