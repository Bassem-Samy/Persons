package com.bassem.persons;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.bassem.persons.ui.login.LoginFragment;
import com.bassem.persons.ui.person.PersonsActivity;
import com.bassem.persons.utils.Constants;
import com.bassem.persons.utils.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkUser();
    }

    /**
     * Checks if there's a saved user token and company domain and loads login fragment or navigates to persons listing
     */
    private void checkUser() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));
        if (TextUtils.isEmpty(helper.getApiToken()) && TextUtils.isEmpty(helper.getCompanyDomain())) {
            initializeLoginFragment();
        } else {
            //navigate to persons page
            navigateToPersonsListing(helper.getCompanyDomain());
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
    public void onSuccessfulLogin(String companyDomain) {
        navigateToPersonsListing(companyDomain);

    }

    /**
     * Updates the base url and navigates to persons listing screen
     *
     * @param companyDomain to update the baseurl with
     */
    private void navigateToPersonsListing(String companyDomain) {
        Constants.updateBaseUrl(companyDomain);
        PersonsActivity.start(this);
        finish();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
