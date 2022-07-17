package com.yan.game.exception;

import liquibase.pro.packaged.O;

public class EntityAbsentException extends Exception {
    private String message;
    public EntityAbsentException(String message) {
        this.message = message;
    }

    public EntityAbsentException() {}

    @Override
    public String getMessage() {
        return "Entity is absent: " + message;
    }

}
