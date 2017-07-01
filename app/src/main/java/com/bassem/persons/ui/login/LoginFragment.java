package com.bassem.persons.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bassem.persons.R;
import com.bassem.persons.ui.login.di.DaggerLoginComponent;
import com.bassem.persons.ui.login.di.LoginModule;
import com.bassem.persons.utils.Constants;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple Fragment to login user
 */
public class LoginFragment extends Fragment implements LoginView {
    public static final String TAG = "login_view";
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.edt_email)
    EditText emailEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    @BindString(R.string.general_error)
    String generalError;
    @BindString(R.string.invalid_email)
    String invalidEmail;
    @BindString(R.string.invalid_password)
    String invalidPassword;
    @BindString(R.string.please_enter_email)
    String pleaseEnterEmail;
    @BindString(R.string.please_enter_password)
    String pleaseEnterPassword;
    @BindString(R.string.password_cant_be_spaces)
    String passwordCantBeSpaces;
    @BindString(R.string.logging_user_in)
    String loggingUserIn;
    @BindString(R.string.wrong_login_combination)
    String wrongLoginCombination;
    @Inject
    LoginPresenter mPresenter;
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this, getContext(), Constants.LOGIN_BASE_URL)).build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
        loginButton.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.INVISIBLE);
        loginButton.setEnabled(true);

    }

    @Override
    public void showError() {
        showToast(generalError);
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showInvalidEmail() {
        showToast(invalidEmail);
    }

    @Override
    public void showInvalidPassword() {
        showToast(invalidPassword);
    }

    @Override
    public void showPassowrdCantBeSpaces() {
        showToast(passwordCantBeSpaces);

    }

    @Override
    public void onLoginSuccessful() {
        if (mListener != null) {
            mListener.onSuccessfulLogin();
        }
    }

    @Override
    public void showEmptyPassword() {
        showToast(pleaseEnterPassword);
    }

    @Override
    public void showEmptyEmail() {
        showToast(pleaseEnterEmail);
    }

    @Override
    public void showLoggingUserIn() {
        showToast(loggingUserIn);

    }

    @Override
    public void showWrongLoginCombination() {
        showToast(wrongLoginCombination);
    }

    void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login)
    void onLoginButtonClick() {
        mPresenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     **/
    public interface OnFragmentInteractionListener {
        void onSuccessfulLogin();
    }
}
