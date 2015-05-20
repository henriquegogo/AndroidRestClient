package io.github.henriquegogo.placarge.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Matches {
    private JSONObject matchesJsonObject;
    public List<Match> matches;

    public Matches(String json) throws JSONException {
        matchesJsonObject = new JSONObject(json);
        JSONArray matchesJsonArray = matchesJsonObject.getJSONArray("matches");

        for (int i = 0; i < matchesJsonArray.length(); i++) {
            Match match = new Match(matchesJsonArray.getJSONObject(i));
            matches.add(match);
        }
    }
}
