package com.bassem.persons.ui.personslisting;

import com.bassem.persons.models.person.PersonsResponse;
import com.bassem.persons.network.PersonsService;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonsListingInteractorImpl implements PersonsListingInteractor {
    Retrofit mRetrofit;

    public PersonsListingInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<PersonsResponse> getPersons(String apiToken, String sort) {
        return mRetrofit.create(PersonsService.class).getPersons(apiToken, sort);
    }
}
