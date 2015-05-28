package io.github.henriquegogo.placarge.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class Team {
    private long id;
    private String hexColor;
    private String name;
    private String shield;
    private String largeShield;

    public Team(JSONObject teamJsonObject) throws JSONException {
        this.id = teamJsonObject.getLong("id");
        this.hexColor = teamJsonObject.getString("color");
        this.name = teamJsonObject.getString("name");
        this.shield = teamJsonObject.getString("shield");
        this.largeShield = teamJsonObject.getString("large_shield");
    }

    public long getId() {
        return id;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getName() {
        return name;
    }

    public String getShield() {
        return shield;
    }

    public String getLargeShield() {
        return largeShield;
    }
}
