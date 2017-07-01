package com.bassem.persons.ui.login.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bassem.persons.ui.login.LoginInteractor;
import com.bassem.persons.ui.login.LoginInteractorImpl;
import com.bassem.persons.ui.login.LoginPresenter;
import com.bassem.persons.ui.login.LoginPresenterImpl;
import com.bassem.persons.ui.login.LoginView;
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
 * Created by Bassem on 7/1/2017.
 */
@Module
public class LoginModule {
    private Context mContext;
    private LoginView mView;
    private String mBaseUrl;

    public LoginModule(LoginView view, Context context, String baseUrl) {
        this.mView = view;
        this.mContext = context;
        this.mBaseUrl = baseUrl;
    }

    // provides context for the creation of SharedPreferencesHelper
    @Singleton
    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    // Provides LoginView for the creation of LoginPresenterImpl
    @Singleton
    @Provides
    public LoginView providesLoginView() {
        return this.mView;
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

    // provides interactor for the creation of LoginPresenter
    @Singleton
    @Provides
    public LoginInteractor providesLoginInteractor(Retrofit retrofit) {
        return new LoginInteractorImpl(retrofit);
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

    @Singleton
    @Provides
    public LoginPresenter providesLoginPresenter(LoginView view, LoginInteractor interactor, SharedPreferencesHelper helper) {
        return new LoginPresenterImpl(view, interactor, helper);
    }

}
