package com.bassem.persons.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bassem.persons.database.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonOperations implements BasicTableOperations<Person> {
    private SQLiteDatabase mSqliteDatabse;

    public PersonOperations(SQLiteDatabase sqLiteDatabase) {
        mSqliteDatabse = sqLiteDatabase;
    }

    /**
     * Creates a person and save it into the database
     *
     * @param person
     */
    @Override
    public void create(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsContract.PersonEntry._ID, person.getId());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
        mSqliteDatabse.insert(PersonsContract.PersonEntry.TABLE_NAME, null, values);
        mSqliteDatabse.close();
    }

    /**
     * Updates a person values
     *
     * @param person
     * @return
     */
    @Override
    public int update(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_PHONES, person.getPhones());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_EMAILS, person.getEmails());

        // updating row
        return mSqliteDatabse.update(PersonsContract.PersonEntry.TABLE_NAME, values, PersonsContract.PersonEntry._ID + " = ?",
                new String[]{String.valueOf(person.getId())});
    }

    /**
     * Deletes a person
     *
     * @param person
     */
    @Override
    public void delete(Person person) {

        mSqliteDatabse.delete(PersonsContract.PersonEntry.TABLE_NAME, PersonsContract.PersonEntry._ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        mSqliteDatabse.close();
    }

    /**
     * insert a list of person in one transaction
     *
     * @param items
     */
    @Override
    public void insertAll(List<Person> items) {
        mSqliteDatabse.beginTransaction();
        for (Person person : items
                ) {
            ContentValues values = new ContentValues();
            values.put(PersonsContract.PersonEntry._ID, person.getId());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_PHONES, person.getPhones());
            values.put(PersonsContract.PersonEntry.COLUMN_NAME_EMAILS, person.getEmails());
            mSqliteDatabse.insertWithOnConflict(PersonsContract.PersonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        mSqliteDatabse.setTransactionSuccessful();
        mSqliteDatabse.endTransaction();
    }

    /**
     * Deletes all person rows from the db
     */
    @Override
    public void deleteAll() {
        mSqliteDatabse.delete(PersonsContract.PersonEntry.TABLE_NAME, null, null);
    }

    /**
     * gets all person records from the database
     *
     * @return
     */
    @Override
    public List<Person> getAll() {
        List<Person> list = new ArrayList<>();
        Cursor cursor = mSqliteDatabse.rawQuery(PersonsContract.PersonEntry.SELECT_ALL_FROM_PERSON, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(cursor.getString(PersonsContract.PersonEntry.ID_COLUMN_INDEX),
                        cursor.getString(PersonsContract.PersonEntry.TITLE_COLUMN_INDEX),
                        cursor.getString(PersonsContract.PersonEntry.IMAGE_COLUMN_INDEX),
                        cursor.getString(PersonsContract.PersonEntry.PHONES_COLUMN_INDEX),
                        cursor.getString(PersonsContract.PersonEntry.EMAILS_COLUMN_INDEX));
                list.add(person);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
