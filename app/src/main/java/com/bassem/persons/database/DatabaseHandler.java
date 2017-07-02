package com.bassem.persons.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bassem on 7/1/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personsManager";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(PersonsContract.PersonEntry.CREATE_PERSONS_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PersonsContract.PersonEntry.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
