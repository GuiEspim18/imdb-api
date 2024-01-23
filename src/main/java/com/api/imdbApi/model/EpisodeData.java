package com.api.imdbApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(
        String Title,
        String Episode,
        @JsonAlias("imdbRating") String Rate,
        String Released
) {
}
