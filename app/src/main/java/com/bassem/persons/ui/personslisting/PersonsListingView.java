package com.bassem.persons.ui.personslisting;

import com.bassem.persons.models.person.PersonData;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingView {
    void showProgress();

    void hideProgress();

    void updateData(List<PersonData> items);

    void showError();
}
