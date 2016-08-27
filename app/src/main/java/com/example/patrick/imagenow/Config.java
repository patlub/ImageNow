package com.example.patrick.imagenow;

/**
 * Created by PATRICK on 8/18/2016.
 */
public class Config {
    //URL to our login.php file
    public static final String ACTIVITY_URL = "http://192.168.1.100/ActivityLog/android_new_activity.php";
    public static final String LOGIN_URL = "http://192.168.1.100/ActivityLog/android_login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String ID_SHARED_PREF = "id";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_DESC = "description";
    public static final String KEY_LOGIN_EMAIL = "user_id";
}