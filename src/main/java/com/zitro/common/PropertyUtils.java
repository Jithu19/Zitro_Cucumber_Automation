package com.zitro.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    //Path of the configuration file
    public static String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "Config" + File.separator;

    //Write the property file
    public static void writeProperty(String filePath, String key, String value) {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error loading properties file for writing.");
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.setProperty(key, value);
            properties.store(outputStream, "Updated properties file");
            System.out.println("Property saved: " + key + " = " + value);
        } catch (IOException e) {
            System.err.println("Error writing to properties file.");
            e.printStackTrace();
        }
    }

    //Read the property file
    public static String readProperty(String filePath, String key) {
        Properties properties = new Properties();
        String value = null;

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            value = properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Error reading from properties file.");
            e.printStackTrace();
        }

        return value;
    }

    /******************************************************************
     * Description : Reads the value corresponding to the given key from the
     *               GlobalData.properties file located at the specified path.
     * Arguments   : String Key - The key whose value needs to be retrieved
     * Return Value: String - The corresponding value if found, otherwise null
     ******************************************************************/
    public static String readGolbalData(String Key) {
        String value = readProperty(PROPERTIES_FILE_PATH  + "GlobalData.properties", Key);
        if (value != null) {
            System.out.println("Value for key '" + Key + "': " + value);
            return value;
        } else {
            System.out.println("Key not found or error reading the property.");
            return null;
        }
    }

}