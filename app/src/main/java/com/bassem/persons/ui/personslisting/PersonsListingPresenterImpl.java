package com.bassem.persons.ui.personslisting;

import android.util.Log;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.models.person.PersonsResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Bassem on 7/1/2017.
 */

public class PersonsListingPresenterImpl implements PersonsListingPresenter {
    private PersonsListingInteractor mInteractor;
    private PersonsListingView mView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PersonsListingPresenterImpl(PersonsListingView view, PersonsListingInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    /**
     * gets the person listing from api, delete all records in the database then saves them
     *
     * @param apiToken
     * @param sort
     */

    @Override
    public void getPersons(String apiToken, String sort) {
        disposeRequest();
        mView.showProgress();
        disposeRequest();
        Disposable disposable = mInteractor.getPersons(apiToken, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonsResponse>() {
                    @Override
                    public void accept(@NonNull PersonsResponse personsResponse) throws Exception {
                        mView.hideProgress();
                        if (personsResponse != null && personsResponse.getData() != null) {
                            //mView.updateData(personsResponse.getData());
                            mInteractor.deleteAllRecords();
                            savePersonsToDatabase(personsResponse.getData());
                        } else {
                            mView.showError();
                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.hideProgress();
                        mView.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        disposeRequest();
    }

    /**
     * saves the person listing that comes from the api into the database
     *
     * @param items
     */
    @Override
    public void savePersonsToDatabase(List<PersonData> items) {
        disposeRequest();
        Disposable disposable = mInteractor.savePersonsToDatabase(items).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean success) throws Exception {
                        if (success) {
                            mView.hideProgress();
                            getPersonsFromDatabase();
                        } else {
                            mView.hideProgress();
                            mView.showError();
                        }
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * Loads all the person rows from the database
     */
    @Override
    public void getPersonsFromDatabase() {
        disposeRequest();
        Disposable disposable = mInteractor.getPersonsFromDatabase().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(@NonNull List<Person> persons) throws Exception {
                        Log.e("size", Integer.toString(persons.size()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    void disposeRequest() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }
}
