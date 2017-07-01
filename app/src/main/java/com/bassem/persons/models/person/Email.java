package com.bassem.persons.models.person;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Email {
    private String primary;

    private String value;

    private String label;

    public String getPrimary ()
    {
        return primary;
    }

    public void setPrimary (String primary)
    {
        this.primary = primary;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

}
