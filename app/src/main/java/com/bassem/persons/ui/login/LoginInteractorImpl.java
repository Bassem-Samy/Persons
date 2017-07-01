package com.bassem.persons.ui.login;

import com.bassem.persons.models.login.LoginModel;
import com.bassem.persons.models.login.LoginResponse;
import com.bassem.persons.network.LoginService;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem on 7/1/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private Retrofit retrofit;

    public LoginInteractorImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Single<LoginResponse> login(LoginModel model) {
        return retrofit.create(LoginService.class).login(model);
    }
}
