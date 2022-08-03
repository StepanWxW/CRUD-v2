package com.stepanwxw.crudv2.exception;


public class NoEntityException extends RuntimeException {


    public NoEntityException(Long id) {
        super(String.valueOf(id));
    }
}
