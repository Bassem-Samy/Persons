package com.bassem.persons.network;

import com.bassem.persons.models.LoginModel;
import com.bassem.persons.models.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface LoginService {

    @Headers("Accept:application/json")
    @POST("authorizations?api_token=null")
    Single<LoginResponse> login(@Body LoginModel model);
}
