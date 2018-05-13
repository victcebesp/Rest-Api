package com.theam.stage2.restapi.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String message){
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
