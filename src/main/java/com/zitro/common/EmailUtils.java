package com.zitro.common;

import java.io.File;
import java.util.Random;

import static com.zitro.common.PropertyUtils.writeProperty;

public class EmailUtils {

    //Path of the GlobalData configuration file
    public static String PROPERTIES_FILE_PATH_GlobalData = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "Config" + File.separator + "GlobalData.properties";

    private static String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static String DOMAIN = "@testzitro.com";
    private static int EMAIL_LENGTH = 13;
    private static int USERNAME_LENGTH = 8;

    private static StringBuilder email;
    private static StringBuilder username;

    /******************************************************************
     * Description : Generates a random email address using predefined
     *               character set and domain suffix.
     * Arguments   : None
     * Return Value: String - The generated email address
     ******************************************************************/
    public static String generateEmail() {
        email = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < EMAIL_LENGTH; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        email.append(DOMAIN);
        System.out.println("Generated Mail: " + email.toString());
        return email.toString();

    }

    /******************************************************************
     * Description : Generates a random username using predefined
     *               character set.
     * Arguments   : None
     * Return Value: String - The generated username
     ******************************************************************/
    public static String generateUserID() {
        username = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < USERNAME_LENGTH; i++) {
            username.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        System.out.println("Generated Username: " + username.toString());
        return username.toString();
    }

    /******************************************************************
     * Description : Stores the most recently generated email and username
     *               into a global properties file for later use.
     * Arguments   : None
     * Return Value: None
     ******************************************************************/
    public static void storeCreatedCredentials() {
        writeProperty(PROPERTIES_FILE_PATH_GlobalData, "email", email.toString());
        writeProperty(PROPERTIES_FILE_PATH_GlobalData, "username", username.toString());
    }
}