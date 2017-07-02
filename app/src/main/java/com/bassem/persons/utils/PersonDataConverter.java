package com.bassem.persons.utils;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.Email;
import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.models.person.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bassem on 7/2/2017.
 */

public class PersonDataConverter {
    public static List<Person> convertAllToDatabasePerson(List<PersonData> items) {
        Gson gson = new Gson();
        List<Person> list = new ArrayList<>();
        Type emailListType = new TypeToken<List<Email>>() {
        }.getType();
        Type phonesListType = new TypeToken<List<Phone>>() {
        }.getType();
        for (PersonData person : items
                ) {
            list.add(new Person(person.getId(),
                    person.getName(),
                    getPersonPicture(person),
                    gson.toJson(person.getPhones(), phonesListType),
                    gson.toJson(person.getEmails(), emailListType)));
        }
        return list;
    }

    /**
     * Checks if person picture of 512 exists return it else return 128
     *
     * @param person
     * @return picture url
     */
    private static String getPersonPicture(PersonData person) {
        if (person.getPictures() != null && person.getPictures().getPictures() != null) {
            return (person.getPictures().getPictures().getUrl512() == null
                    || person.getPictures().getPictures().getUrl512().trim() == "") ?
                    person.getPictures().getPictures().geturl128() : person.getPictures().getPictures().getUrl512();
        }
        return null;
    }
}
