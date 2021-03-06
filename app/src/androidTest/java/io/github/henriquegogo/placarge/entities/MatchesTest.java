package io.github.henriquegogo.placarge.entities;

import android.test.InstrumentationTestCase;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchesTest extends InstrumentationTestCase {

    public void testParseMatchesJSON() throws JSONException {
        String json = "{ \"matches\": [{ \"id\": 1, \"link_of_match\": \"http://google.com\", \"dt_of_match\": \"2014-12-07T19:00:00\", \"where\": \"mineirao\", \"home_team\": { \"id\":2, \"name\": \"Cruzeiro\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\": \"#000000\" }, \"guest_team\": { \"id\":1, \"name\": \"Fluminense\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\":\"#000000\" }, \"home_team_id\": 2, \"home_team_score\": 2, \"guest_team_id\": 1, \"guest_team_score\": 3}] }";
        Matches matches = new Matches(json);

        assertEquals(1, matches.getMatches().size());
    }

    public void testGetTeams() throws JSONException {
        String json = "{ \"matches\": [{ \"id\": 1, \"link_of_match\": \"http://google.com\", \"dt_of_match\": \"2014-12-07T19:00:00\", \"where\": \"mineirao\", \"home_team\": { \"id\":2, \"name\": \"Cruzeiro\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\": \"#000000\" }, \"guest_team\": { \"id\":1, \"name\": \"Fluminense\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\":\"#000000\" }, \"home_team_id\": 2, \"home_team_score\": 2, \"guest_team_id\": 1, \"guest_team_score\": 3}] }";
        Matches matches = new Matches(json);

        assertEquals(2, matches.getTeams().size());
        assertEquals("Cruzeiro", matches.getTeams().get(0).getName());
        assertEquals("Fluminense", matches.getTeams().get(1).getName());
    }
}
