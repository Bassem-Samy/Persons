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
        public static final String COLUMN_NAME_PHONES = "phones";
        public static final String COLUMN_NAME_EMAILS = "emails";
        public static final String CREATE_PERSONS_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY," + COLUMN_NAME_TITLE + " TEXT,"
                + COLUMN_NAME_IMAGE_URL + " TEXT," + COLUMN_NAME_PHONES + " TEXT," + COLUMN_NAME_EMAILS + " TEXT" + ")";
        public static final String SELECT_ALL_FROM_PERSON = "SELECT * FROM " + TABLE_NAME;
        public static final String SELECT_ALL_FROM_PERSON_ORDER_BY_NAME_ASC="SELECT * FROM "+TABLE_NAME+" ORDER BY "+ COLUMN_NAME_TITLE+" ASC";
        public static final int ID_COLUMN_INDEX = 0;
        public static final int TITLE_COLUMN_INDEX = 1;
        public static final int IMAGE_COLUMN_INDEX = 2;
        public static final int PHONES_COLUMN_INDEX = 3;
        public static final int EMAILS_COLUMN_INDEX = 4;
        public static final java.lang.String DELETE_ALL_FROM_TABLE = "DELTE * FROM " + TABLE_NAME;
    }

}
