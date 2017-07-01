package com.bassem.persons.utils;

/**
 * Created by Bassem on 7/2/2017.
 */

public enum PersonsSortFields {
    NAME_ASC("name ASC"),
    NAME_DESC("name DESC");
    private final String text;

    private PersonsSortFields(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
