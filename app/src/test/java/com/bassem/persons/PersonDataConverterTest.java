package com.bassem.persons;

import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonsResponse;
import com.bassem.persons.utils.PersonDataConverter;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * this class tests PersonDataConverter class, that it converts PersonData models to Person data correctly to be saved in the db
 * Created by Bassem on 7/4/2017.
 */

public class PersonDataConverterTest {

    PersonsResponse response;
    // Expected first item values in response list
    static final int EXPECTED_ITEMS_COUNT = 5;
    static final String EXPECTED_FIRST_ITEM_ID = "1";
    static final String EXPECTED_FIRST_ITEM_NAME = "Josh Garrels";
    static final String EXPECTED_FIRST_ITEM_IMAGE_URL = "https://pipedrive-profile-pics.s3.amazonaws.com/f549f896d1b143ee6ecf8009e4cb33786c053893_128.jpg";
    static final String EXPECTED_FIRST_ITEM_PHONES_JSON = "[{\"primary\":\"true\",\"value\":\"+12345678910\",\"label\":\"work\"},{\"primary\":\"false\",\"value\":\"+23645678910\",\"label\":\"home\"}]";
    static final String EXPECTED_FIRST_ITEM_EMAILS_JSON = "[{\"primary\":\"true\",\"value\":\"josh@email.com\",\"label\":\"work\"},{\"primary\":\"false\",\"value\":\"Josh2@josh.com\",\"label\":\"home\"}]";
    // Expected last item values in response list
    private static final String EXPECTED_LAST_ITEM_NAME = "Amy Macdonald";
    private static final String EXPECTED_LAST_ITEM_PHONES_JSON = "[{\"primary\":\"true\",\"value\":\"1230131313\",\"label\":\"work\"}]";
    private static final String EXPECTED_LAST_ITEM_EMAILS_JSON = "[{\"primary\":\"true\",\"value\":\"amy.music@email.com\",\"label\":\"work\"}]";
    private static final String EXPECTED_LAST_ITEM_ID = "5";
    private static final String EXPECTED_LAST_ITEM_IMAGE_URL = "https://pipedrive-profile-pics.s3.amazonaws.com/2c5b6744e41afbf6558b2fe5b28dcb9172760a38_128.jpg";

    @Before
    public void setup() throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream("person_response.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        Gson gson = new Gson();
        response = gson.fromJson(builder.toString(), PersonsResponse.class);
    }

    @Test
    public void testConverterPersonList() {
        List<Person> items = PersonDataConverter.convertAllToDatabasePerson(response.getData());
        assertEquals(items.size(), EXPECTED_ITEMS_COUNT);
        assertEquals(items.get(0).getName(), EXPECTED_FIRST_ITEM_NAME);
        assertEquals(items.get(0).getPhones(), EXPECTED_FIRST_ITEM_PHONES_JSON);
        assertEquals(items.get(0).getEmails(), EXPECTED_FIRST_ITEM_EMAILS_JSON);
        assertEquals(items.get(0).getId(), EXPECTED_FIRST_ITEM_ID);
        assertEquals(items.get(0).getImageUrl(), EXPECTED_FIRST_ITEM_IMAGE_URL);

        // test last item
        assertEquals(items.get(4).getName(), EXPECTED_LAST_ITEM_NAME);
        assertEquals(items.get(4).getPhones(), EXPECTED_LAST_ITEM_PHONES_JSON);
        assertEquals(items.get(4).getEmails(), EXPECTED_LAST_ITEM_EMAILS_JSON);
        assertEquals(items.get(4).getId(), EXPECTED_LAST_ITEM_ID);
        assertEquals(items.get(4).getImageUrl(), EXPECTED_LAST_ITEM_IMAGE_URL);

    }
}
