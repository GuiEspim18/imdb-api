package com.api.imdbApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
        Integer Number,
        List<EpisodeData> Episodes
) {
}
