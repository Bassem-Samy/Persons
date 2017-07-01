package com.bassem.persons.ui.personslisting;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingPresenter {
    void getPersons(String apiToken,String sort);
    void onDestroy();
}
