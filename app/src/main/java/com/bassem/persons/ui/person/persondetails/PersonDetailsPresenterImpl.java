package com.bassem.persons.ui.person.persondetails;

import com.bassem.persons.models.person.PersonDetail;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bassem on 7/3/2017.
 */

public class PersonDetailsPresenterImpl implements PersonDetailsPresenter {
    private PersonDetailsView mView;
    private PersonDetailsInteractor mInteractor;
    private Disposable mDisposable;

    public PersonDetailsPresenterImpl(PersonDetailsView view, PersonDetailsInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public void getPersonData(String personId) {
        disposeRequest();
        mView.showProgress();
        mDisposable = mInteractor.getPersonDetailsFromDatabaseById(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonDetail>() {
                    @Override
                    public void accept(@NonNull PersonDetail personDetail) throws Exception {
                        mView.hideProgress();
                        mView.updateDetails(personDetail);
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
    public void destroy() {
        disposeRequest();
    }

    void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
