package com.api.imdbApi.services.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter implements IConverter {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T convert(final String JSON, final Class<T> CLASS) {
        try {
            return MAPPER.readValue(JSON, CLASS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
