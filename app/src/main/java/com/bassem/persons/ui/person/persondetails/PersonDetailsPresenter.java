package com.bassem.persons.ui.person.persondetails;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonDetail;

/**
 * Created by Bassem on 7/3/2017.
 */

public interface PersonDetailsPresenter {
    void getPersonData(String personId);
    void destroy();

}
