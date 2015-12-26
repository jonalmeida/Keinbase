package com.jonalmeida.keinbase.util;

public class ApiKey {
    // Remove the surrounding comments after adding your key.
    private static String CLIENT_SECRET /* = "CLIENT_SECRET_HERE" */;
    private static String CLIENT_ID /* = "CLIENT_ID_HERE" */;
    private static final String REDIRECT_URI = "keinbase://coinbase-oauth";

    public static String getClientSecret() {
        if (CLIENT_SECRET == null) {
            throw new NullPointerException("Secret key not set. Get one at Coinbase and place it in ApiKey.java");
        }
        return CLIENT_SECRET;
    }

    public static String getClientId() {
        if (CLIENT_ID == null) {
            throw new NullPointerException("Client ID not set. Get one at Coinbase and place it in ApiKey.java");
        }
        return CLIENT_ID;
    }

    public static String getRedirectUri() {
        return REDIRECT_URI;
    }
}
