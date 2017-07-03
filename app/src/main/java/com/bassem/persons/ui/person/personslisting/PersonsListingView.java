package com.bassem.persons.ui.person.personslisting;

import com.bassem.persons.database.models.Person;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingView {
    void showProgress();

    void hideProgress();

    void updateData(List<Person> items);

    void showError();
}
