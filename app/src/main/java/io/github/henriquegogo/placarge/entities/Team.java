package io.github.henriquegogo.placarge.entities;

import org.json.JSONObject;

public class Team {
    private JSONObject teamJsonObject;
    private long id;
    private String hexColor;
    private String name;
    private String shield;
    private String largeShield;

    public Team(JSONObject teamJsonObject) {
        this.teamJsonObject = teamJsonObject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    public String getLargeShield() {
        return largeShield;
    }

    public void setLargeShield(String largeShield) {
        this.largeShield = largeShield;
    }
}
