package com.bassem.persons.ui.login;

import android.text.TextUtils;

import com.bassem.persons.models.login.LoginModel;
import com.bassem.persons.models.login.LoginResponse;
import com.bassem.persons.utils.Constants;
import com.bassem.persons.utils.SharedPreferencesHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Bassem on 7/1/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginInteractor mInteractor;
    private LoginView mView;
    private Disposable mDisposable;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor, SharedPreferencesHelper sharedPreferencesHelper) {
        this.mInteractor = interactor;
        this.mView = view;
        this.mSharedPreferencesHelper = sharedPreferencesHelper;
    }

    @Override
    public void login(String email, String password) {
        disposeRequest();
        if (!validate(email, password)) {
            return;
        }
        mView.showProgress();
        mView.showLoggingUserIn();
        mDisposable = mInteractor.login(new LoginModel(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LoginResponse>() {
                            @Override
                            public void accept(@NonNull LoginResponse loginResponse) throws Exception {
                                mView.hideProgress();
                                if (loginResponse != null &&
                                        loginResponse.getSuccess() && loginResponse.getData() != null && loginResponse.getData().size() > 0) {
                                    saveAPIToken(loginResponse.getData().get(0).getApiToken());
                                    saveCompanyDomain(loginResponse.getData().get(0).getCompany().getInfo().getDomain());
                                    mView.onLoginSuccessful();
                                } else {
                                    if (!TextUtils.isEmpty(loginResponse.getError())) {
                                        mView.showMessage(loginResponse.getError());
                                    } else {
                                        mView.showError();
                                    }
                                }

                            }
                        }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {

                                mView.hideProgress();
                                if (throwable instanceof HttpException) {
                                    if (((HttpException) throwable).code() == 400) {
                                        mView.showWrongLoginCombination();
                                    } else {
                                        mView.showError();
                                    }
                                } else {
                                    mView.showError();
                                }
                            }
                        });

    }


    @Override
    public void onDestroy() {
        disposeRequest();
    }

    @Override
    public boolean validate(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            mView.showEmptyEmail();
            return false;
        }
        if (!validateEmailFormat(email)) {
            mView.showInvalidEmail();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mView.showEmptyPassword();
            return false;
        }
        if (password.length() < Constants.PASSWORD_MINIMUM_LENGTH) {
            mView.showInvalidPassword();
            return false;
        }
        if (password.trim().length() == 0) {
            mView.showPassowrdCantBeSpaces();
            return false;
        }
        return true;
    }

    @Override
    public void saveAPIToken(String token) {
        mSharedPreferencesHelper.saveApiToken(token);
    }

    @Override
    public void saveCompanyDomain(String companyDomain) {
        mSharedPreferencesHelper.saveCompanyDomain(companyDomain);
    }

    private void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private boolean validateEmailFormat(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
