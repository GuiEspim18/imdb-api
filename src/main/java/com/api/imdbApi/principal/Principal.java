package com.api.imdbApi.principal;

import com.api.imdbApi.model.SerieData;
import com.api.imdbApi.services.consumeApi.ConsumeApiService;
import com.api.imdbApi.services.converter.Converter;

import java.util.Scanner;

public class Principal {

    private static final String ADDRESS = "https://omdbapi.com/?t=";

    private static final String API_KEY = "&apikey=6585022c";

    private static Scanner scanner = new Scanner(System.in);

    public static void show () {
        System.out.println("Digite o nome de uma série: ");
        final String SERIE = scanner.nextLine().replace(" ", "+").toLowerCase();
        var result = Converter.convert(ConsumeApiService.get(ADDRESS + SERIE + API_KEY), SerieData.class);
        System.out.println(result);
    }

}
