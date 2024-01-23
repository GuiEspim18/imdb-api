package com.api.imdbApi.principal;

import com.api.imdbApi.exceptions.InvalidOptionException;
import com.api.imdbApi.model.SerieData;
import com.api.imdbApi.services.consumeApi.ConsumeApiService;
import com.api.imdbApi.services.converter.Converter;
import com.api.imdbApi.services.messages.Messages;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Principal {

    private static final String ADDRESS = "https://omdbapi.com/?t=";

    private static final String API_KEY = "&apikey=6585022c";

    private static Scanner scanner = new Scanner(System.in);

    public static void show() {
        showMenu();
        validate();
    }

    private static void showMenu() {
        System.out.println("___________________________________");
        System.out.println("Choose an option:");
        List<String> options = List.of(new String[]{"Get a serie", "Get a serie episode", "Get a serie season"});
        IntStream.range(0, options.size()).forEach(i -> {
            Integer index = i + 1;
            final String MESSAGE = MessageFormat.format("{0}: {1}", index, options.get(i));
            System.out.println(MESSAGE);
        });
        System.out.println("___________________________________");
    }

    private static void validate () {
        try {
            Integer option = Integer.parseInt(scanner.nextLine());
            getOptions(option);
        } catch (InvocationTargetException | IllegalAccessException | InvalidOptionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getOptions(Integer option) throws InvocationTargetException, IllegalAccessException, InvalidOptionException {
        switch (option) {
            case 1:
                showSerie();
                break;

            case 2:
                break;

            case 3:
                break;

            default:
                throw new InvalidOptionException();
        }
    }

    private static void showSerie() throws InvocationTargetException, IllegalAccessException {
        System.out.print("Tap a serie name: ");
        final String SERIE = scanner.nextLine().replace(" ", "+").toLowerCase();
        var result = Converter.convert(ConsumeApiService.get(ADDRESS + SERIE + API_KEY), SerieData.class);
        Messages.showSerie(result);
    }


}
