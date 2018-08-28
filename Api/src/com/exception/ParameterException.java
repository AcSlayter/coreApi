package com.exception;

/**
 * Created by aaron on 5/27/2018.
 */
public class ParameterException extends Exception {

    public ParameterException (String message) {
        super (message);
        System.out.println(message);
    }
}
