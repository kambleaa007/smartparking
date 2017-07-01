package com.techmahindra.smartparking.common.exception.application;

/**
 * GenericApplicationException.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public class GenericApplicationException extends AbstractBaseApplicationException {
    private static final long serialVersionUID = 1L;

    /**
     * Super AbstractBaseApplicationException class default constructor call
     */
    public GenericApplicationException() {
        super();
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * and cause
     * 
     * @param message
     * @param cause
     */
    public GenericApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * 
     * @param message
     */
    public GenericApplicationException(String message) {
        super(message);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by cause
     * 
     * @param cause
     */
    public GenericApplicationException(Throwable cause) {
        super(cause);
    }
}