package com.bassem.persons.ui.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bassem.persons.MainActivity;
import com.bassem.persons.R;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.ui.person.persondetails.PersonDetailsFragment;
import com.bassem.persons.ui.person.personslisting.PersonsListingFragment;
import com.bassem.persons.utils.LogoutHelper;
import com.bassem.persons.utils.SharedPreferencesHelper;

public class PersonsActivity extends AppCompatActivity implements PersonsListingFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        initializePersonsFragment();
    }

    private void initializePersonsFragment() {
        if (getSupportFragmentManager().findFragmentByTag(PersonsListingFragment.TAG) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frm_container, PersonsListingFragment.newInstance(), PersonsListingFragment.TAG)
                    .commit();
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Person person) {
        initializePersonDetailsFragment(person.getId());
    }

    private void initializePersonDetailsFragment(String id) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(PersonDetailsFragment.TAG);
        if (fragment == null) {
            fragment = PersonDetailsFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frm_details_container, fragment, PersonDetailsFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            ((PersonDetailsFragment) fragment).loadPersonDetails(id);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_menu_item: {
                if (LogoutHelper.logOut(new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this)))) {
                    MainActivity.start(this);
                    finish();
                }

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
