package com.techmahindra.smartparking.common.exception.application;

/**
 * UnhandledResponseCodeApplicationException.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public class UnhandledResponseCodeApplicationException extends AbstractBaseApplicationException {
    private static final long serialVersionUID = 1L;

    /**
     * Super AbstractBaseApplicationException class default constructor call
     */
    public UnhandledResponseCodeApplicationException() {
        super();
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * and cause
     * 
     * @param message
     * @param cause
     */
    public UnhandledResponseCodeApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * 
     * @param message
     */
    public UnhandledResponseCodeApplicationException(String message) {
        super(message);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by cause
     * 
     * @param cause
     */
    public UnhandledResponseCodeApplicationException(Throwable cause) {
        super(cause);
    }
}