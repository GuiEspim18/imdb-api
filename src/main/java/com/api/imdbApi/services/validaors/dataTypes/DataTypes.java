package com.api.imdbApi.services.validaors.dataTypes;

import java.util.List;

public class DataTypes {

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static boolean isList(Object obj) {
        return obj instanceof List;
    }

}
