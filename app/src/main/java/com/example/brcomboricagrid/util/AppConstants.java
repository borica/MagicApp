package com.example.brcomboricagrid.util;

public class AppConstants {

    private static final String ROUTE_PATH = "http://10.0.2.2:3333/"; //LOCALHOST IP ---- ONLY WORKS WITH EMULATOR/LOCALHOST IF IP IS 10.0.2.2

    public static final String CREATE_CARD_ROUTE = "create-card";
    public static final String UPDATE_CARD_ROUTE = "update-card";
    public static final String DELETE_CARD_ROUTE = "delete-card";
    public static final String LIST_CARD_ROUTE = "list-card";

    public static String getFullRoute(String route) {

        switch (route) {
            case CREATE_CARD_ROUTE:
                return ROUTE_PATH + CREATE_CARD_ROUTE;
            case UPDATE_CARD_ROUTE:
                return ROUTE_PATH + UPDATE_CARD_ROUTE;
            case DELETE_CARD_ROUTE:
                return ROUTE_PATH + DELETE_CARD_ROUTE;
            case LIST_CARD_ROUTE:
                return ROUTE_PATH + LIST_CARD_ROUTE;
            default: break;
        }

        return null;
    }

}
