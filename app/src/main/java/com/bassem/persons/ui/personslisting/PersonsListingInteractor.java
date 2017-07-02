package com.bassem.persons.ui.personslisting;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.models.person.PersonsResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingInteractor {
    Single<PersonsResponse> getPersons(String apiToken, String sort);

    Single<Boolean> savePersonsToDatabase(List<PersonData> items);

    Single<List<Person>> getPersonsFromDatabase();
    void deleteAllRecords();
}
