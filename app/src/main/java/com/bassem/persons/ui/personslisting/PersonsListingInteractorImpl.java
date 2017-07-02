package com.bassem.persons.ui.personslisting;

import com.bassem.persons.database.BasicTableOperations;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.models.person.PersonsResponse;
import com.bassem.persons.network.PersonsService;
import com.bassem.persons.utils.PersonDataConverter;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonsListingInteractorImpl implements PersonsListingInteractor {
    Retrofit mRetrofit;
    BasicTableOperations<Person> mBasicTableOperations;

    public PersonsListingInteractorImpl(Retrofit retrofit, BasicTableOperations<Person> basicTableOperations) {
        mRetrofit = retrofit;
        this.mBasicTableOperations = basicTableOperations;
    }

    /**
     * returns person response from the api
     *
     * @param apiToken
     * @param sort     parameter for the returned data
     * @return
     */
    @Override
    public Single<PersonsResponse> getPersons(String apiToken, String sort) {
        return mRetrofit.create(PersonsService.class).getPersons(apiToken, sort);
    }

    /**
     * saves a list of Person Data into "Person" table in the database
     *
     * @param items
     * @return
     */
    @Override
    public Single<Boolean> savePersonsToDatabase(final List<PersonData> items) {

        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    // uses PersonDataConverter to convert the person model that comes from the api to the person db model
                    mBasicTableOperations.insertAll(PersonDataConverter.convertAllToDatabasePerson(items));
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    /**
     * Loads all person records from the database
     *
     * @return
     */
    @Override
    public Single<List<Person>> getPersonsFromDatabase() {
        return Single.fromCallable(new Callable<List<Person>>() {
            @Override
            public List<Person> call() throws Exception {
                return mBasicTableOperations.getAll();
            }
        });
    }

    /**
     * clear the person table from all records
     */
    @Override
    public void deleteAllRecords() {
        mBasicTableOperations.deleteAll();
    }
}
