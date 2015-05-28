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

    public List<Match> getMatches(Team... teams) {
        if (teams.length > 0 && teams[0] != null) {
            Team team = teams[0];
            List<Match> matchesFiltered = new ArrayList<>();

            for (int i = 0; i < matches.size(); i++) {
                Match match = matches.get(i);
                if (match.getHomeTeam().getId() == team.getId() ||
                    match.getGuestTeam().getId() == team.getId())
                    matchesFiltered.add(match);
            }

            return matchesFiltered;

        } else {
            return matches;
        }
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        List<Long> teamIds = new ArrayList<>();

        // Deveria ser feito com lambda expression, caso a versão do Java suportasse.
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
