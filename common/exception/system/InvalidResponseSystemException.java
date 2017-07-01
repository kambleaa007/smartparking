package com.techmahindra.smartparking.common.exception.system;

/**
 * InvalidResponseSystemException.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public class InvalidResponseSystemException extends AbstractBaseSystemException {

    private static final long serialVersionUID = 1L;

    /**
     * super AbstractBaseSystemException class default constructor call
     */
    public InvalidResponseSystemException() {

        super();
    }

    /**
     * super AbstractBaseSystemException class constructor call by message and
     * cause
     * 
     * @param message
     * @param cause
     */
    public InvalidResponseSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * super AbstractBaseSystemException class constructor call by message
     * 
     * @param message
     */
    public InvalidResponseSystemException(String message) {
        super(message);
    }

    /**
     * super AbstractBaseSystemException class constructor call by cause
     * 
     * @param cause
     */
    public InvalidResponseSystemException(Throwable cause) {

        super(cause);
    }
}