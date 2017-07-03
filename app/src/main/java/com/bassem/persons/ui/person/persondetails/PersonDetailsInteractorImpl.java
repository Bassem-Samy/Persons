package com.bassem.persons.ui.person.persondetails;

import android.text.TextUtils;
import android.util.Log;

import com.bassem.persons.database.BasicTableOperations;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.Email;
import com.bassem.persons.models.person.PersonDetail;
import com.bassem.persons.models.person.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by Bassem on 7/3/2017.
 */

public class PersonDetailsInteractorImpl implements PersonDetailsInteractor {
    private static final String NEW_LINE = "\n";
    private static final String LABEL_SEPARATOR = ": ";
    BasicTableOperations<Person> mBasicTableOperations;
    Gson mGson;

    public PersonDetailsInteractorImpl(BasicTableOperations<Person> operations, Gson gson) {
        mBasicTableOperations = operations;
        this.mGson = gson;
    }

    @Override
    public Single<PersonDetail> getPersonDetailsFromDatabaseById(final String id) {
        return Single.fromCallable(new Callable<PersonDetail>() {
            @Override
            public PersonDetail call() throws Exception {
                Person person = mBasicTableOperations.getRecordById(id);
                return new PersonDetail(
                        person.getId(),
                        person.getName(),
                        person.getImageUrl(),
                        getPhonesDisplayText(person.getPhones()),
                        getEmailsDisplayText(person.getEmails())
                );
            }
        });
    }

    @Override
    public String getPhonesDisplayText(String json) {
        Type phoneListType = new TypeToken<List<Phone>>() {
        }.getType();
        StringBuilder builder = new StringBuilder();
        try {
            List<Phone> phones = mGson.fromJson(json, phoneListType);
            for (int i = 0; i < phones.size(); i++) {
                builder.append(phones.get(i).getLabel())
                        .append(LABEL_SEPARATOR)
                        .append(phones.get(i).getValue());
                if (i < phones.size() - 1) {
                    builder.append(NEW_LINE);
                }
            }
            return builder.toString();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
            return null;
        }
    }

    @Override
    public String getEmailsDisplayText(String json) {

        Type emailListTYpe = new TypeToken<List<Email>>() {
        }.getType();
        try {
            List<Email> emails = mGson.fromJson(json, emailListTYpe);
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < emails.size(); i++) {
                builder.append(emails.get(i).getLabel())
                        .append(LABEL_SEPARATOR)
                        .append(emails.get(i).getValue());
                if (i < emails.size() - 1) {
                    builder.append(NEW_LINE);
                }
            }
            return builder.toString();
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
            return null;
        }
    }
}
