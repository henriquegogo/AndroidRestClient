package io.github.henriquegogo.placarge.entities;

import android.test.InstrumentationTestCase;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchTest extends InstrumentationTestCase {

    public void testCreateMatchFromJSONObject() throws JSONException {
        int ID = 1;
        String LINK_OF_MATCH = "http://google.com";
        String DATE_OF_MATCH = "2014-12-07T19:00:00";
        String WHERE = "mineirao";
        int HOME_TEAM_ID = 2;
        int GUEST_TEAM_ID = 1;
        int HOME_TEAM_SCORE = 2;
        int GUEST_TEAM_SCORE = 3;

        String json = "{ \"id\": "+ID+", \"link_of_match\": \""+LINK_OF_MATCH+"\"," +
                "\"dt_of_match\": \""+DATE_OF_MATCH+"\", \"where\": \""+WHERE+"\"," +
                "\"home_team\": { \"id\": "+HOME_TEAM_ID+", \"name\": \"Cruzeiro\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\": \"#000000\" }," +
                "\"guest_team\": { \"id\": "+GUEST_TEAM_ID+", \"name\": \"Fluminense\", \"shield\": \"shield.jpg\", \"large_shield\": \"lshield.jpg\", \"color\":\"#000000\" }," +
                "\"home_team_id\": "+HOME_TEAM_ID+", \"home_team_score\": "+HOME_TEAM_SCORE+"," +
                "\"guest_team_id\": "+GUEST_TEAM_ID+", \"guest_team_score\": "+GUEST_TEAM_SCORE+"}";

        JSONObject jsonObject = new JSONObject(json);

        Match match = new Match(jsonObject);

        assertEquals(ID, match.getId());
        assertEquals(LINK_OF_MATCH, match.getLink());
        assertEquals(DATE_OF_MATCH, match.getDate());
        assertEquals(WHERE, match.getWhere());
        assertEquals(HOME_TEAM_ID, match.getHomeTeam().getId());
        assertEquals(HOME_TEAM_SCORE, match.getHomeScore());
        assertEquals(GUEST_TEAM_ID, match.getGuestTeam().getId());
        assertEquals(GUEST_TEAM_SCORE, match.getGuestScore());
    }
}
