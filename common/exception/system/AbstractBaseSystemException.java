package com.techmahindra.smartparking.common.exception.system;

/**
 * AbstractBaseSystemException.java The base exception class to handle all
 * system level exceptions like DB connection, Network connection
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public abstract class AbstractBaseSystemException extends Exception {
    private static final long serialVersionUID = -55593587738246853L;

    /**
     * Super Exception class default constructor call
     */
    public AbstractBaseSystemException() {
        super();
    }

    /**
     * Super Exception class constructor call by message
     * 
     * @param message
     */
    public AbstractBaseSystemException(String message) {
        super(message);
    }

    /**
     * Super Exception class constructor call by cause
     * 
     * @param cause
     */
    public AbstractBaseSystemException(Throwable cause) {
        super(cause);
    }

    /**
     * Super Exception class constructor call by message and cause
     * 
     * @param message
     * @param cause
     */
    public AbstractBaseSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}