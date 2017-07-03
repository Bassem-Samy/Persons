package com.bassem.persons.ui.person.personslisting;

import com.bassem.persons.models.person.PersonData;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingPresenter {
    void getPersons(String apiToken, String sort);

    void onDestroy();

    void savePersonsToDatabase(List<PersonData> items);
    void getPersonsFromDatabase();
}
