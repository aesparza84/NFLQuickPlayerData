package com.app.NFLPlayers.exceptions;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(String msg) {
        super(msg);
    }
}
