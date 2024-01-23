package com.api.imdbApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(
        String Title,
        String Genre,
        String Director,
        String Writer,
        String Actors,
        String Year,
        @JsonAlias("totalSeasons") String Seasons
) {
}

// {"Title":"Modern Family","Year":"2009–2020","Rated":"TV-PG","Released":"23 Sep 2009","Runtime":"22 min","Genre":"Comedy, Drama, Romance","Director":"N/A","Writer":"Steven Levitan, Christopher Lloyd","Actors":"Ed O'Neill, Sofía Vergara, Julie Bowen","Plot":"Three different but related families face trials and tribulations in their own uniquely comedic ways.","Language":"English, Spanish, Chinese, Italian","Country":"United States","Awards":"Won 22 Primetime Emmys. 122 wins & 384 nominations total","Poster":"https://m.media-amazon.com/images/M/MV5BNzRhNWIxYTEtYjc2NS00YWFlLWFhOGEtMDZiMWM1M2RkNDkyXkEyXkFqcGdeQXVyNjc0MjkzNjc@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"8.5/10"}],"Metascore":"N/A","imdbRating":"8.5","imdbVotes":"471,272","imdbID":"tt1442437","Type":"series","totalSeasons":"11","Response":"True"}
