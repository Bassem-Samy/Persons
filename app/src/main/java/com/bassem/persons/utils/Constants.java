package com.bassem.persons.utils;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Constants {
    public static String LOGIN_BASE_URL = "https://api.pipedrive.com/v1/";
    public static final String COMPANY_DOMAIN_PATH = "COMPANY_DOMAIN";
    public static String BASE_URL = "https://" + COMPANY_DOMAIN_PATH + ".pipedrive.com/v1/";
    public static final int PASSWORD_MINIMUM_LENGTH = 8;

    public static void updateBaseUrl(String companyDomain) {
        BASE_URL = BASE_URL.replace(COMPANY_DOMAIN_PATH, companyDomain);
    }
}
