package com.techmahindra.smartparking.common.aspects.logger.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ExceptionLoggingAspect.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Aspect
@Component
public class ExceptionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class); // Initializing
                                                                                                // logger
    private final ThreadLocal<Throwable> lastLoggedExceptionHolder = new ThreadLocal<>();
    String user = null;

    /**
     * All exceptions will be caught here and logged
     * 
     * @param point
     * @param throwable
     */
    @AfterThrowing(pointcut = "execution(* com.techmahindra.smartparking..*.*(..))", throwing = "throwable")
    public void logExceptionAfterThrowing(final JoinPoint point, final Throwable throwable) {
        final Throwable lastLoggedException = lastLoggedExceptionHolder.get();
        if (lastLoggedException == null) {
            lastLoggedExceptionHolder.set(throwable);
        } else
            if (lastLoggedException == throwable) {
                return;
            }

        final Class<? extends Object> targetClass = point.getTarget().getClass();
        final Signature methodSignature = point.getSignature();
        logger.error("*********** Exception occurred in method " + methodSignature.toShortString()
                + " of " + targetClass + " for user " + user + " ***********", throwable);
    }
}