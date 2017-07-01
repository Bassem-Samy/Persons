package com.bassem.persons.ui.login.di;

import com.bassem.persons.ui.login.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem on 7/1/2017.
 */
@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginFragment target);
}
