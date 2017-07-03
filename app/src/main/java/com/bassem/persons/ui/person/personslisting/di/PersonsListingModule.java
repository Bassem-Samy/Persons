package com.bassem.persons.ui.person.personslisting.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bassem.persons.database.BasicTableOperations;
import com.bassem.persons.database.DatabaseHandler;
import com.bassem.persons.database.PersonOperations;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.ui.person.personslisting.PersonsListingInteractor;
import com.bassem.persons.ui.person.personslisting.PersonsListingInteractorImpl;
import com.bassem.persons.ui.person.personslisting.PersonsListingPresenter;
import com.bassem.persons.ui.person.personslisting.PersonsListingPresenterImpl;
import com.bassem.persons.ui.person.personslisting.PersonsListingView;
import com.bassem.persons.utils.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bassem on 7/2/2017.
 */
@Module
public class PersonsListingModule {

    private Context mContext;
    private PersonsListingView mView;
    private String mBaseUrl;

    public PersonsListingModule(PersonsListingView view, Context context, String baseUrl) {
        this.mContext = context;
        this.mView = view;
        this.mBaseUrl = baseUrl;
    }

    // provides context for the creation of SharedPreferencesHelper
    @Singleton
    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    // Provides PersonsListingView for the creation of PersonsListingPresenter
    @Singleton
    @Provides
    public PersonsListingView providesPersonsListingView() {
        return this.mView;
    }

    // provides shared preferences for the creation of shared preferences helper
    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // provides shared prefernces helper
    @Singleton
    @Provides
    public SharedPreferencesHelper providesSharedPreferencesHelper(SharedPreferences sharedPreferences) {

        return new SharedPreferencesHelper(sharedPreferences);
    }
    // provides converter factory for retrofit

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    // provides call adapter factory for retrofit
    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    // provides a retrofit
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(mBaseUrl)
                .build();
    }

    @Singleton
    @Provides
    public DatabaseHandler providesDatabaseHandler(Context context) {
        return new DatabaseHandler(context);
    }

    @Singleton
    @Provides

    public BasicTableOperations<Person> providesBasicTableOperations(DatabaseHandler handler) {
        return new PersonOperations(handler.getWritableDatabase());
    }

    @Singleton
    @Provides
    public PersonsListingInteractor ProvidesPersonsListingInteractor(Retrofit retrofit, BasicTableOperations<Person> basicTableOperations) {
        return new PersonsListingInteractorImpl(retrofit, basicTableOperations);
    }

    @Singleton
    @Provides
    public PersonsListingPresenter providesPersonsListingPresenter(PersonsListingView view, PersonsListingInteractor interactor) {
        return new PersonsListingPresenterImpl(view, interactor);
    }
}

