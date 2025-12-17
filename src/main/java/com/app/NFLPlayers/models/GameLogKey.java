package com.app.NFLPlayers.models;

import java.io.Serializable;
import java.util.Objects;

public class GameLogKey implements Serializable {
    private String name;
    private String opposition;

    public GameLogKey(){}


    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opposition) {
        this.opposition = opposition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        GameLogKey compKey = (GameLogKey) obj;

        return compKey.name.equals(this.name) &&
                compKey.opposition.equals(this.opposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, opposition);
    }
}
