package io.github.henriquegogo.placarge.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Matches {
    private JSONObject matchesJsonObject;
    public List<Match> matches = new ArrayList<>();

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

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
        return teams;
    }
}
