package com.nyw.cateringsystem.exception;

import lombok.Data;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className AlreadyExistException
 */
public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message){
        super(message);
    }

    public AlreadyExistException() {
    }
}
