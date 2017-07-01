package com.bassem.persons.network;

import com.bassem.persons.models.person.PersonsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface PersonsService {
    @GET("persons/")
    Single<PersonsResponse> getPersons(@Query("api_token") String apiToken,@Query("sort")String sort);
}
