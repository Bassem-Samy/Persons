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
        ContentValues values = createPersoContentValues(person);
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
        ContentValues values = createPersoContentValues(person);
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
            ContentValues values = createPersoContentValues(person);
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
        //   Cursor cursor = mSqliteDatabse.rawQuery(PersonsContract.PersonEntry.SELECT_ALL_FROM_PERSON, null);
        Cursor cursor = mSqliteDatabse.rawQuery(PersonsContract.PersonEntry.SELECT_ALL_FROM_PERSON_ORDER_BY_NAME_ASC, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = createPersonFromCursor(cursor);

                list.add(person);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * gets a person by id
     *
     * @param id
     * @return
     */
    @Override
    public Person getRecordById(String id) {
        Cursor cursor = mSqliteDatabse.query(PersonsContract.PersonEntry.TABLE_NAME,
                new String[]{
                        PersonsContract.PersonEntry._ID, PersonsContract.PersonEntry.COLUMN_NAME_TITLE, PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, PersonsContract.PersonEntry.COLUMN_NAME_PHONES, PersonsContract.PersonEntry.COLUMN_NAME_EMAILS
                }, PersonsContract.PersonEntry._ID + "=?",
                new String[]{id}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return createPersonFromCursor(cursor);
        }
        return null;
    }

    /**
     * helper method to create a person object from a cursor of person
     *
     * @param cursor
     * @return
     */
    Person createPersonFromCursor(Cursor cursor) {
        return new Person(cursor.getString(PersonsContract.PersonEntry.ID_COLUMN_INDEX),
                cursor.getString(PersonsContract.PersonEntry.TITLE_COLUMN_INDEX),
                cursor.getString(PersonsContract.PersonEntry.IMAGE_COLUMN_INDEX),
                cursor.getString(PersonsContract.PersonEntry.PHONES_COLUMN_INDEX),
                cursor.getString(PersonsContract.PersonEntry.EMAILS_COLUMN_INDEX));

    }

    /**
     * populates content values for person
     *
     * @param person
     * @return
     */
    ContentValues createPersoContentValues(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_TITLE, person.getName());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_IMAGE_URL, person.getImageUrl());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_PHONES, person.getPhones());
        values.put(PersonsContract.PersonEntry.COLUMN_NAME_EMAILS, person.getEmails());
        return values;
    }
}
