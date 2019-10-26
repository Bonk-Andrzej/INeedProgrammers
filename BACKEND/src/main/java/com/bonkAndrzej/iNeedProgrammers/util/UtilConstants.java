package com.bonkAndrzej.iNeedProgrammers.util;

/**
 * Application constants.
 */
public final class UtilConstants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    public static final String phoneNumberRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";


    private UtilConstants() {
    }
}
