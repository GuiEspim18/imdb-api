package com.api.imdbApi.principal;

import com.api.imdbApi.exceptions.InvalidOptionException;
import com.api.imdbApi.exceptions.NotFoundEpisodeException;
import com.api.imdbApi.exceptions.NotFoundSeasonException;
import com.api.imdbApi.model.EpisodeData;
import com.api.imdbApi.model.SeasonData;
import com.api.imdbApi.model.SerieData;
import com.api.imdbApi.services.consumeApi.ConsumeApiService;
import com.api.imdbApi.services.converter.Converter;
import com.api.imdbApi.services.messages.Messages;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {

    private static final String ADDRESS = "https://omdbapi.com/?t=";

    private static final String EPISODE = "&episode=";

    private static final String SEASON = "&season=";

    private static final String API_KEY = "&apikey=6585022c";

    private static Scanner scanner = new Scanner(System.in);

    private static SerieData serie;

    public static void show() {
        showMenu();
        validate();
    }

    private static void showMenu() {
        System.out.println("___________________________________");
        System.out.println("Choose an option:");
        List<String> options = Arrays.asList("Get a serie", "Get a serie episode", "Get a serie season", "Get bests episodes from a serie");
        IntStream.range(0, options.size()).forEach(i -> {
            int index = i + 1;
            final String MESSAGE = MessageFormat.format("{0}: {1}", index, options.get(i));
            System.out.println(MESSAGE);
        });
        System.out.println("___________________________________");
    }

    private static void validate () {
        try {
            Integer option = Integer.parseInt(scanner.nextLine());
            getOptions(option);
        } catch (InvocationTargetException | IllegalAccessException | InvalidOptionException | NotFoundSeasonException |
                 NotFoundEpisodeException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getOptions(Integer option) throws InvocationTargetException, IllegalAccessException, InvalidOptionException, NotFoundSeasonException, NotFoundEpisodeException {
        switch (option) {
            case 1: showSerie(); break;
            case 2: showEpisode(); break;
            case 3: showSeason(); break;
            case 4: showBestEpisodes(); break;
            default: throw new InvalidOptionException();
        }
    }

    private static void showSerie() throws InvocationTargetException, IllegalAccessException {
        System.out.print("Tap a serie name: ");
        final String SERIE = scanner.nextLine().replace(" ", "+").toLowerCase();
        var result = Converter.convert(ConsumeApiService.get(ADDRESS + SERIE + API_KEY), SerieData.class);
        Messages.show(result);
        serie = result;
    }

    private static void showEpisode() throws InvocationTargetException, IllegalAccessException, NotFoundSeasonException, NotFoundEpisodeException {
        showSerie();
        var message = MessageFormat.format("Tap a season of {0}: ", serie.Title());
        System.out.print(message);
        final int LIMIT = Integer.parseInt(serie.Seasons());
        final int CUR_SEASON = Integer.parseInt(scanner.nextLine());
        final String SERIE = serie.Title().replace(" ", "+").toLowerCase();
        if (CUR_SEASON < 1 && CUR_SEASON > LIMIT ) {
            throw new NotFoundSeasonException();
        }
        message = MessageFormat.format("Tap an episode of season {0}: ", CUR_SEASON);
        System.out.print(message);
        final int CUR_EPISODE = Integer.parseInt(scanner.nextLine());
        var result = Converter.convert(ConsumeApiService.get(ADDRESS + SERIE + SEASON + CUR_SEASON + EPISODE + CUR_EPISODE + API_KEY), EpisodeData.class);
        if (result.Title().isEmpty()) {
            throw new NotFoundEpisodeException();
        }
        Messages.show(result);

    }

    private static void showSeason() throws InvocationTargetException, IllegalAccessException, NotFoundSeasonException {
        showSerie();
        var message = MessageFormat.format("Tap a season of {0}: ", serie.Title());
        System.out.print(message);
        final int LIMIT = Integer.parseInt(serie.Seasons());
        final int CUR_SEASON = Integer.parseInt(scanner.nextLine());
        final String SERIE = serie.Title().replace(" ", "+").toLowerCase();
        if (CUR_SEASON < 1 && CUR_SEASON > LIMIT ) {
            throw new NotFoundSeasonException();
        }
        var result = Converter.convert(ConsumeApiService.get(ADDRESS + SERIE + SEASON + CUR_SEASON + API_KEY), SeasonData.class);
        Messages.show(result);
    }


    private static void showBestEpisodes() throws InvocationTargetException, IllegalAccessException {
        showSerie();
        List<SeasonData> seasons = new ArrayList<>();
        final String SERIE = serie.Title().replace(" ", "+").toLowerCase();
        System.out.println("Loading...");
        for (int i = 1; i <= Integer.parseInt(serie.Seasons()); i++) {
            var result = ConsumeApiService.get(ADDRESS + SERIE + SEASON + i + API_KEY);
            SeasonData season = Converter.convert(result, SeasonData.class);
            seasons.add(season);
        }

        // pegando todos os episodios de todas as temporadas e jogandod entro de um array
        // podemos usar o .collect(Collectors.toList()) ou o .toList mas o .toList vai gerar uma lista imutavel que não podemos adicionair mais itens
        List<EpisodeData> episodeData = seasons.stream()
                                              .flatMap(s -> s.Episodes().stream())
                                              .collect(Collectors.toList());
        episodeData.stream()
                   .filter(e -> !e.Rate().equalsIgnoreCase("N/A"))
                   .sorted(Comparator.comparing(EpisodeData::Rate).reversed())
                   .limit(5)
                   .forEach(System.out::println);

//        Messages.show((Record) episodeData);

    }


}
