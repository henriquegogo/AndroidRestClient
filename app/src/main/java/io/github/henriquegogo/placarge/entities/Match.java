package io.github.henriquegogo.placarge.entities;

import org.json.JSONException;
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

    public Match(JSONObject matchJsonObject) throws JSONException {
        this.matchJsonObject = matchJsonObject;

        this.id = matchJsonObject.getLong("id");
        this.homeScore = matchJsonObject.getLong("home_team_score");
        this.guestScore = matchJsonObject.getLong("guest_team_score");
        this.date = matchJsonObject.getString("dt_of_match");
        this.where = matchJsonObject.getString("where");
        this.link = matchJsonObject.getString("link_of_match");
        this.homeTeam = new Team(matchJsonObject.getJSONObject("home_team"));
        this.guestTeam = new Team(matchJsonObject.getJSONObject("guest_team"));
    }

    public long getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public long getHomeScore() {
        return homeScore;
    }

    public long getGuestScore() {
        return guestScore;
    }

    public String getDate() {
        return date;
    }

    public String getWhere() {
        return where;
    }

    public String getLink() {
        return link;
    }
}