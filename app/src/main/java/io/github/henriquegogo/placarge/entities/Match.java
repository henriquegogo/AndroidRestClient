package io.github.henriquegogo.placarge.entities;

import org.json.JSONObject;

public class Match {
    private JSONObject matchJsonObject;
    private long id;
    private Team homeTeam;
    private Team guestTeam;
    private long homeScore;
    private long guestScore;
    private String date;
    private String where;
    private String link;

    public Match(JSONObject matchJsonObject) {
        this.matchJsonObject = matchJsonObject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public long getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(long homeScore) {
        this.homeScore = homeScore;
    }

    public long getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(long guestScore) {
        this.guestScore = guestScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}