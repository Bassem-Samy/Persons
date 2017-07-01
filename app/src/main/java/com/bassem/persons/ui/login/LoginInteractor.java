package com.bassem.persons.ui.login;

import com.bassem.persons.models.LoginModel;
import com.bassem.persons.models.LoginResponse;

import io.reactivex.Single;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface LoginInteractor {
    Single<LoginResponse> login(LoginModel model);
}
