package com.nyw.cateringsystem.exception;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className NormalException
 */
public class SourceNotFoundException extends RuntimeException{
    public SourceNotFoundException(String message){
        super(message);
    }

    public SourceNotFoundException() {
    }
}
