package com.bassem.persons.ui.login;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface LoginPresenter {
    void login(String email, String password);
    void onDestroy();
    boolean validate(String email,String password);
    void saveAPIToken(String token);
    void saveCompanyDomain(String companyDomain);

}
