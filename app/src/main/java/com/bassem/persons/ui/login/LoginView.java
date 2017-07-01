package com.bassem.persons.ui.login;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void showError();

    void showMessage(String msg);

    void showInvalidEmail();

    void showInvalidPassword();

    void showPassowrdCantBeSpaces();

    void onLoginSuccessful();

    void showEmptyPassword();

    void showEmptyEmail();
    void showLoggingUserIn();

    void showWrongLoginCombination();
}
