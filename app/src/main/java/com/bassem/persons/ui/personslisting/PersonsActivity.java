package com.bassem.persons.ui.personslisting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bassem.persons.R;
import com.bassem.persons.database.models.Person;

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

    }
}
