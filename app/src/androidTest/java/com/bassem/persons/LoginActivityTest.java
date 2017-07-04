package com.bassem.persons;

import android.preference.PreferenceManager;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bassem.persons.utils.SharedPreferencesHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Bassem on 7/4/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void clearEditTexts() {

        SharedPreferencesHelper mhelper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(mActivityRule.getActivity()));
        mhelper.saveCompanyDomain(null);
        mhelper.saveApiToken(null);
        onView(withId(R.id.edt_email)).perform(ViewActions.clearText());
        onView(withId(R.id.edt_password)).perform(ViewActions.clearText());
    }

    // test for empty email, should show please enter email toast
    @Test
    public void testEmptyEmailLoginValidation() {

        onView(withId(R.id.edt_email)).perform(ViewActions.typeText(""));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.please_enter_email)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        waitForToast();
    }

    // test for invalid email, should show invalid email toast
    @Test
    public void testInvalidEmailLoginValidation() {
        onView(withId(R.id.edt_email)).perform(ViewActions.typeText("invalid@email"));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.invalid_email)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        waitForToast();
    }

    // test for correct email but no password, should show please enter password toast
    @Test
    public void testEmptyPasswordValidation() {
        onView(withId(R.id.edt_email)).perform(ViewActions.typeText("email@email.com"));
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText(""));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.please_enter_password)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        waitForToast();
    }

    // test for correct email but invalid password, should show please enter a valid password toast
    @Test
    public void testPasswordValidation() {
        onView(withId(R.id.edt_email)).perform(ViewActions.typeText("email@email.com"));
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText("123"));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.invalid_password)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        waitForToast();
    }

    // test for correct email but spaces password, should show please enter a valid password toast
    @Test
    public void testPasswordSpacesValidation() {
        onView(withId(R.id.edt_email)).perform(ViewActions.typeText("email@email.com"));
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText("           "));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.password_cant_be_spaces)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        waitForToast();
    }


    /**
     * Function to wait until the toast is hidden, in order to accurately find the toast text
     * because sometimes the toast is delayed to be shown until the previous toast is hidden.
     */
    public static void waitForToast() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
