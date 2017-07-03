package com.bassem.persons.ui.person.persondetails;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonDetail;

import io.reactivex.Single;

/**
 * Created by Bassem on 7/3/2017.
 */

public interface PersonDetailsInteractor {
    Single<PersonDetail> getPersonDetailsFromDatabaseById(String id);

    String getPhonesDisplayText(String json);

    String getEmailsDisplayText(String json);
}
