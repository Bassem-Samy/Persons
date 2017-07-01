package com.bassem.persons.ui.personslisting;

import com.bassem.persons.models.person.PersonsResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
    private Disposable mDisposable;

    public PersonsListingPresenterImpl(PersonsListingView view, PersonsListingInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public void getPersons(String apiToken, String sort) {
        mView.showProgress();
        disposeRequest();
        mDisposable = mInteractor.getPersons(apiToken, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonsResponse>() {
                    @Override
                    public void accept(@NonNull PersonsResponse personsResponse) throws Exception {
                        mView.hideProgress();
                        if (personsResponse != null && personsResponse.getData() != null) {
                            mView.updateData(personsResponse.getData());
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
    }

    @Override
    public void onDestroy() {
        disposeRequest();
    }

    void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
