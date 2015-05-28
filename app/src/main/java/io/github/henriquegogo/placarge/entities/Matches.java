package io.github.henriquegogo.placarge.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Matches {
    private JSONObject matchesJsonObject;
    private List<Match> matches = new ArrayList<>();

    public Matches(String json) {
        try {
            matchesJsonObject = new JSONObject(json);
            JSONArray matchesJsonArray = matchesJsonObject.getJSONArray("matches");

            for (int i = 0; i < matchesJsonArray.length(); i++) {
                Match match = new Match(matchesJsonArray.getJSONObject(i));
                matches.add(match);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        List<Long> teamIds = new ArrayList<>();

        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            Team homeTeam = match.getHomeTeam();
            Team guestTeam = match.getGuestTeam();

            if (!teamIds.contains(homeTeam.getId())) teams.add(homeTeam);
            if (!teamIds.contains(guestTeam.getId())) teams.add(guestTeam);

            teamIds.add(homeTeam.getId());
            teamIds.add(guestTeam.getId());
        }

        return teams;
    }
}

//
