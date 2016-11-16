package fr.upem.webservice.app;

import java.util.Map;

/**
 * Created by raptao on 11/7/2016.
 */
public class Sessions {
    public final static String LOGGED_IN_USER = "loggedInUser";
    public final static String CAR_WEB_SERVICE = "carWebService";
    public final static String PRODUCTS = "allProducts";
    public final static String CURRENT_CURRENCY = "currency";
    public final static String SELECTED_CAR = "selectedCar";

    public static Object getFromSession(Map<String, Object> userSession, String key) {
        return userSession.get(key);
    }

    public static void unset(Map<String, Object> userSession, String key) {
        userSession.remove(key);
    }
}
