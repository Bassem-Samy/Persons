package com.bassem.persons.ui.person.persondetails;

import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.models.person.PersonDetail;

/**
 * Created by Bassem on 7/3/2017.
 */

public interface PersonDetailsView {
    void showProgress();

    void hideProgress();

    void updateDetails(PersonDetail data);

    void showError();
}
