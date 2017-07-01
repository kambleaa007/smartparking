package com.techmahindra.smartparking.common.exception.application;

/**
 * AbstractBaseApplicationException.java The base exception class handling all
 * logical exceptions
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public abstract class AbstractBaseApplicationException extends Exception {
    private static final long serialVersionUID = -8668912792019661800L;

    /**
     * Super exception class constructor call
     */
    public AbstractBaseApplicationException() {
        super();
    }

    /**
     * Super Exception class constructor call by some message
     * 
     * @param message
     */
    public AbstractBaseApplicationException(String message) {
        super(message);
    }

    /**
     * Super Exception class constructor call by cause
     * 
     * @param cause
     */
    public AbstractBaseApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * super Exception class constructor call by message and cause
     * 
     * @param message
     * @param cause
     */
    public AbstractBaseApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}