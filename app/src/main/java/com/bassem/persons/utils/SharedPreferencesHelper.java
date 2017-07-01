package com.bassem.persons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem on 7/1/2017.
 */

public class SharedPreferencesHelper {
    private static final String API_TOKEN_KEY = "api_token";
    private static final String COMPANY_DOMAIN_KEY = "company_domain";
    SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public boolean saveApiToken(String token) {
        return mSharedPreferences.edit().putString(API_TOKEN_KEY, token).commit();

    }

    public boolean saveCompanyDomain(String companyDomain) {
        return mSharedPreferences.edit().putString(COMPANY_DOMAIN_KEY, companyDomain).commit();
    }

    public String getApiToken() {
        return mSharedPreferences.getString(API_TOKEN_KEY, null);
    }

    public String getCompanyDomain() {
        return mSharedPreferences.getString(COMPANY_DOMAIN_KEY, null);
    }
}
