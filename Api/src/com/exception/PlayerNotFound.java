package com.exception;

/**
 * Created by aaron on 5/29/2018.
 */
public class PlayerNotFound extends Exception {

    public PlayerNotFound(String name) {
        super("Player : ".concat(name).concat(" Not Found"));
    }
}
