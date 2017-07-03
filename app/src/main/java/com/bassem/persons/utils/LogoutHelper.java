package com.bassem.persons.utils;

import android.preference.PreferenceManager;

/**
 * Created by Bassem on 7/3/2017.
 */

public class LogoutHelper {
    public static boolean logOut(SharedPreferencesHelper helper) {
        return (helper.saveApiToken(null) && helper.saveCompanyDomain(null));
    }
}
