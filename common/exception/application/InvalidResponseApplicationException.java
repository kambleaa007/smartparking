package com.techmahindra.smartparking.common.exception.application;

/**
 * InvalidResponseApplicationException.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public class InvalidResponseApplicationException extends AbstractBaseApplicationException {
    private static final long serialVersionUID = 1L;

    /**
     * Super AbstractBaseApplicationException class default constructor call
     */
    public InvalidResponseApplicationException() {

        super();
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * and cause
     * 
     * @param message
     * @param cause
     */
    public InvalidResponseApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * 
     * @param message
     */
    public InvalidResponseApplicationException(String message) {
        super(message);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by cause
     * 
     * @param cause
     */
    public InvalidResponseApplicationException(Throwable cause) {
        super(cause);
    }
}