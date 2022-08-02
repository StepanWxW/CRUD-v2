package com.stepanwxw.crudv2.repository;


public class NoEntityException extends RuntimeException {


    public NoEntityException(Long id) {
        super(String.valueOf(id));
    }
}
