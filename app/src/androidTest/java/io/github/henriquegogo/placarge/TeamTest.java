package io.github.henriquegogo.placarge;

import android.test.InstrumentationTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.henriquegogo.placarge.entities.Team;

public class TeamTest extends InstrumentationTestCase {
    int ID = 1;
    String NAME = "Cruzeiro";
    String SHIELD = "shield.jpg";
    String LARGE_SHIELD = "lshield.jpg";
    String COLOR = "#000000";

    public void testCreateTeamFromJSONObject() throws JSONException {
        String json = "{ \"id\": "+ID+", \"name\": \""+NAME+"\"," +
                "\"shield\": \""+SHIELD+"\", \"large_shield\": \""+LARGE_SHIELD+"\"," +
                "\"color\": \""+COLOR+"\" }";

        JSONObject jsonObject = new JSONObject(json);

        Team team = new Team(jsonObject);

        assertEquals(ID, team.getId());
        assertEquals(NAME, team.getName());
        assertEquals(SHIELD, team.getShield());
        assertEquals(LARGE_SHIELD, team.getLargeShield());
        assertEquals(COLOR, team.getHexColor());
    }
}
