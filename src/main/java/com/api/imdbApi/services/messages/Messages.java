package com.api.imdbApi.services.messages;

import com.api.imdbApi.model.SerieData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.text.MessageFormat;


public class Messages {

    public static void showSerie (SerieData data) throws InvocationTargetException, IllegalAccessException {
        RecordComponent[] fields = data.getClass().getRecordComponents();
        System.out.println("___________________________________");
        for (RecordComponent field : fields) {
            final String MESSAGE = MessageFormat.format("{0}: {1}", field.getName(), field.getAccessor().invoke(data));
            System.out.println(MESSAGE);
        }
    }


}
