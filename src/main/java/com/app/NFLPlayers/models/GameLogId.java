package com.app.NFLPlayers.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class GameLogId implements Serializable {
    public String playerName;
    public LocalDate date;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        GameLogId g = (GameLogId) obj;
        return playerName.equals(g.playerName) &&
                date == g.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, date);
    }
}
