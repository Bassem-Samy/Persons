package com.bassem.persons.database;

import android.provider.BaseColumns;

/**
 * Created by Bassem on 7/1/2017.
 */

public final class PersonsContract {
    private PersonsContract() {
    }

    public static class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMAGE_URL = "picture_url";
        public static final String CREATE_PERSONS_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY," + COLUMN_NAME_TITLE + " TEXT,"
                + COLUMN_NAME_IMAGE_URL + " TEXT" + ")";
    }

}
