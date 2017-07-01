package com.techmahindra.smartparking.common.exception.application;

/**
 * GenericDBException.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public class GenericDBException extends AbstractBaseApplicationException {
    private static final long serialVersionUID = 1L;

    /**
     * Super AbstractBaseApplicationException class default constructor call
     */
    public GenericDBException() {
        super();
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * and cause
     * 
     * @param message
     * @param cause
     */
    public GenericDBException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * 
     * @param message
     */
    public GenericDBException(String message) {
        super(message);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by cause
     * 
     * @param cause
     */
    public GenericDBException(Throwable cause) {
        super(cause);
    }
}