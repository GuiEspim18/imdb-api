package com.api.imdbApi.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeApiService {

    public static String get(final String ADDRESS) {
        final HttpClient CLIENT = HttpClient.newHttpClient();
        final HttpRequest REQUEST = HttpRequest.newBuilder()
                                    .uri(URI.create(ADDRESS))
                                    .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(REQUEST, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

}
