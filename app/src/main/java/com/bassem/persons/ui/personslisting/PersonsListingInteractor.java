package com.bassem.persons.ui.personslisting;

import com.bassem.persons.models.person.PersonsResponse;

import io.reactivex.Single;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsListingInteractor {
Single<PersonsResponse> getPersons(String apiToken,String sort);
}
