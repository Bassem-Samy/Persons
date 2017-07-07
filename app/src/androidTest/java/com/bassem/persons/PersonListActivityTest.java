package com.bassem.persons;

import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bassem.persons.ui.person.PersonsActivity;
import com.bassem.persons.utils.Constants;
import com.bassem.persons.utils.SharedPreferencesHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Bassem on 7/7/2017.
 */
@RunWith(AndroidJUnit4.class)

public class PersonListActivityTest {
    static final int TOTAL_ITEMS_COUNT = 10;
    private static final String PERSON_RESPONSE_FILE_NAME = "person_response.json";
    private static final int EXPECTED_PERSON_COUNT = 18;
    private static final String LAST_ITEM_EXPECTED_NAME = "Tod Freil";
    private static final String FIRST_ITEM_EXPECTED_NAME = "Amy Macdonald";
    @Rule
    public  ActivityTestRule<PersonsActivity> mActivityRule = new ActivityTestRule<>(PersonsActivity.class);

    @BeforeClass
    public  static void setup() throws IOException {
       // SharedPreferencesHelper helper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(mActivityRule.getActivity()));
      //  helper.saveApiToken("dummy");
      //  helper.saveCompanyDomain("dummy");
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        Constants.BASE_URL = mockWebServer.url("test/").toString();
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(AssetsFileReader.readFileAsString(PERSON_RESPONSE_FILE_NAME));
        mockResponse.setResponseCode(200);
        mockWebServer.enqueue(mockResponse);
    }

    @Test
    public void checkPersonListing() {
        // first check the total number of displayed item is correct
        onView(withId(R.id.rclr_person)).check(new RecyclerViewItemsCountAssertion(EXPECTED_PERSON_COUNT));
        // then scroll to the last item and check if the displayed name is correct
        onView(withId(R.id.rclr_person)).perform(scrollToPosition(EXPECTED_PERSON_COUNT - 1));
        onView(withId(R.id.rclr_person)).check(new RecyclerViewItemStringDataAssertion(R.id.txt_person_name,
                LAST_ITEM_EXPECTED_NAME,
                EXPECTED_PERSON_COUNT - 1));

        // Scroll to the first item and then check if the displayed name is correct
        onView(withId(R.id.rclr_person)).perform(scrollToPosition(0));
        onView(withId(R.id.rclr_person)).check(new RecyclerViewItemStringDataAssertion(R.id.txt_person_name,
                FIRST_ITEM_EXPECTED_NAME,
                0));


    }

    @After
    public void clearSharedPreferences() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(mActivityRule.getActivity()));
        helper.saveApiToken(null);
        helper.saveCompanyDomain(null);
    }
}
