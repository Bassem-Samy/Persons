package com.bassem.persons.utils;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bassem on 7/2/2017.
 */

public class PersonDataConverter {
    public static List<Person> convertAllToDatabasePerson(List<PersonData> items) {
        List<Person> list = new ArrayList<>();

        for (PersonData person : items
                ) {
            list.add(new Person(person.getId(), person.getName(), getPersonPicture(person)));
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
