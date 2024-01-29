package com.api.imdbApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {
    private Integer Season;
    private String Title;
    private Integer Episode;
    private Double Rate;
    private LocalDate Released;

    public Episode(Integer season, EpisodeData dataEpisode) {
        this.Season = season;
        this.Title = dataEpisode.Title();
        this.Episode = Integer.parseInt(dataEpisode.Episode());
        try {
            this.Rate = Double.valueOf(dataEpisode.Rate());
        } catch (NumberFormatException ex) {
            this.Rate = 0.0;
        }

        try {
            this.Released = LocalDate.parse(dataEpisode.Released());
        } catch (DateTimeException ex) {
            this.Released = null;
        }

    }

    public Integer getSeason() {
        return Season;
    }

    public void setSeason(Integer season) {
        Season = season;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getEpisode() {
        return Episode;
    }

    public void setEpisode(Integer episode) {
        Episode = episode;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public LocalDate getReleased() {
        return Released;
    }

    public void setReleased(LocalDate released) {
        Released = released;
    }

    @Override
    public String toString () {
        return "Episode {" +
                "season=" + Season +
                ", title=" + Title +
                ", episode=" + Episode +
                ", rate=" + Rate +
                ", released=" + Released +
                "}";
    }
}
