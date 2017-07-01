package com.bassem.persons;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.bassem.persons.ui.login.LoginFragment;
import com.bassem.persons.utils.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkUser();
    }

    private void checkUser() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));
        if (TextUtils.isEmpty(helper.getApiToken()) && TextUtils.isEmpty(helper.getCompanyDomain())) {
            initializeLoginFragment();
        } else {
            //navigate to persons page
        }
    }

    private void initializeLoginFragment() {
        if (getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frm_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .commit();

        }
    }

    @Override
    public void onSuccessfulLogin() {

    }
}
