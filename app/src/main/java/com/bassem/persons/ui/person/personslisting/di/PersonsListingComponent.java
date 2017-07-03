package com.bassem.persons.ui.person.personslisting.di;

import com.bassem.persons.ui.person.personslisting.PersonsListingFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem on 7/2/2017.
 */
@Singleton
@Component(modules = {PersonsListingModule.class})
public interface PersonsListingComponent {
    void inject(PersonsListingFragment target);
}
