package com.uniquehire.rolemanagement.exception;

public class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException(String message){
        super(message);
    }
}