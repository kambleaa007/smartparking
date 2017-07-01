package com.techmahindra.smartparking.common.exception.application;

public class ProcessingFailureException extends AbstractBaseApplicationException {
    private static final long serialVersionUID = 1L;

    /**
     * Super AbstractBaseApplicationException class default constructor call
     */
    public ProcessingFailureException() {

        super();
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * and cause
     * 
     * @param message
     * @param cause
     */
    public ProcessingFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by message
     * 
     * @param message
     */
    public ProcessingFailureException(String message) {
        super(message);
    }

    /**
     * Super AbstractBaseApplicationException class constructor call by cause
     * 
     * @param cause
     */
    public ProcessingFailureException(Throwable cause) {
        super(cause);
    }

}
