package com.bassem.persons.ui.person.persondetails.di;

import com.bassem.persons.ui.person.persondetails.PersonDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem on 7/3/2017.
 */
@Singleton
@Component(modules = {PersonDetailsModule.class})
public interface PersonDetailsComponent {
    void inject(PersonDetailsFragment target);
}
