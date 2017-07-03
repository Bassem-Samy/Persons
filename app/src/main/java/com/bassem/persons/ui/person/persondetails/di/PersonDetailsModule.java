package com.bassem.persons.ui.person.persondetails.di;

import android.content.Context;

import com.bassem.persons.database.BasicTableOperations;
import com.bassem.persons.database.DatabaseHandler;
import com.bassem.persons.database.PersonOperations;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonDetail;
import com.bassem.persons.ui.person.persondetails.PersonDetailsInteractor;
import com.bassem.persons.ui.person.persondetails.PersonDetailsInteractorImpl;
import com.bassem.persons.ui.person.persondetails.PersonDetailsPresenter;
import com.bassem.persons.ui.person.persondetails.PersonDetailsPresenterImpl;
import com.bassem.persons.ui.person.persondetails.PersonDetailsView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bassem on 7/3/2017.
 */
@Module
public class PersonDetailsModule {
    private PersonDetailsView mView;
    private Context mContext;

    public PersonDetailsModule(PersonDetailsView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    // Provides context to create the person operations for db
    @Singleton
    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    //Provide the Person details view
    @Singleton
    @Provides
    public PersonDetailsView providesPersonDetailsView() {
        return this.mView;
    }

    // provides database handler for the person table operatiosn
    @Singleton
    @Provides
    public DatabaseHandler providesDatabaseHandler(Context context) {
        return new DatabaseHandler(context);
    }

    // provides basic table operations of person to the interactor
    @Singleton
    @Provides
    public BasicTableOperations<Person> providesBasicTableOperations(DatabaseHandler handler) {
        return new PersonOperations(handler.getWritableDatabase());
    }

    // provides gson for the creation of the
    @Singleton
    @Provides
    public Gson providesGson() {
        return new Gson();
    }

    // provides the interactor
    @Singleton
    @Provides
    public PersonDetailsInteractor providesPersonDetailsInteractor(BasicTableOperations<Person> basicTableOperations, Gson gson) {
        return new PersonDetailsInteractorImpl(basicTableOperations, gson);
    }

    // provides the presenter
    @Singleton
    @Provides
    public PersonDetailsPresenter providesPersonDetailsPresenter(PersonDetailsView view, PersonDetailsInteractor interactor) {
        return new PersonDetailsPresenterImpl(view, interactor);
    }
}
