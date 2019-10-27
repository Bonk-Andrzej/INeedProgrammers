package com.bonkAndrzej.iNeedProgrammers.util;

/**
 * Application constants.
 */
public final class UtilConstants {

    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    //    public static final String phoneNumberRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public static final String phoneNumberRegex = "^((\\+|00)359|0)(\\-|\\s)?8[7-9][2-9](\\-|\\s)?\\d{3}(\\s|\\-)?\\d{3}$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymousUser";


    private UtilConstants() {
    }
}
