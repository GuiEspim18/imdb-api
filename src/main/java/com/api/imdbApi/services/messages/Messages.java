package com.api.imdbApi.services.messages;

import com.api.imdbApi.model.EpisodeData;
import com.api.imdbApi.model.SeasonData;
import com.api.imdbApi.model.SerieData;
import com.api.imdbApi.services.validaors.dataTypes.DataTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;


public class Messages {

    public static void show(Record data) throws InvocationTargetException, IllegalAccessException {
        RecordComponent[] fields = data.getClass().getRecordComponents();
        System.out.println("___________________________________");
        for (RecordComponent field : fields) {
            var getData = field.getAccessor().invoke(data);
            final boolean IS_LIST = DataTypes.isList(getData);
            var message = "";
            if (IS_LIST) {
                List<EpisodeData> list = (List<EpisodeData>) Arrays.asList(getData).get(0);
                String episodes = "";
                int index = 1;
                for (EpisodeData item : list) {
                    episodes += MessageFormat.format("{0}: {1} \n", index, item.Title());
                    index++;
                }
                message = MessageFormat.format("{0}: \n{1}", field.getName(), episodes);
            } else {
                message = MessageFormat.format("{0}: {1}", field.getName(), getData);
            }
            System.out.println(message);
        }
        System.out.println("___________________________________");
    }

}
