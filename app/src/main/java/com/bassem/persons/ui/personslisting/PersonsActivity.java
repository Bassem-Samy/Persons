package com.bassem.persons.ui.personslisting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bassem.persons.R;
import com.bassem.persons.models.person.PersonData;

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
    public void onFragmentInteraction(PersonData person) {

    }
}
