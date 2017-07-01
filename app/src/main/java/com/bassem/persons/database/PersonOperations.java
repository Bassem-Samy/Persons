package com.bassem.persons.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bassem.persons.database.models.Person;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonOperations implements BasicTableOperations<Person> {
    private SQLiteDatabase mSqliteDatabse;

    public PersonOperations(SQLiteDatabase sqLiteDatabase) {
        mSqliteDatabse = sqLiteDatabase;
    }

    @Override
    public void create(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsContract.PersonEntry._ID, person.getId());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
        mSqliteDatabse.insert(PersonsContract.PersonEntry.TABLE_NAME, null, values);
        mSqliteDatabse.close();
    }

    @Override
    public int update(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());

        // updating row
        return mSqliteDatabse.update(PersonsContract.PersonEntry.TABLE_NAME, values, PersonsContract.PersonEntry._ID + " = ?",
                new String[]{String.valueOf(person.getId())});
    }

    @Override
    public void delete(Person person) {

        mSqliteDatabse.delete(PersonsContract.PersonEntry.TABLE_NAME, PersonsContract.PersonEntry._ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        mSqliteDatabse.close();
    }

    @Override
    public void insertAll(List<Person> items) {
        mSqliteDatabse.beginTransaction();
        for (Person person : items
                ) {
            ContentValues values = new ContentValues();
            values.put(PersonsContract.PersonEntry._ID, person.getId());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
            mSqliteDatabse.insertWithOnConflict(PersonsContract.PersonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        mSqliteDatabse.setTransactionSuccessful();
        mSqliteDatabse.endTransaction();
    }
}
