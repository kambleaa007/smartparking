package com.techmahindra.smartparking.common.aspects.logger.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * TransactionLogAspect.java Log all the transactions and will connect to the
 * JMS service
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Aspect
@Component
public class TransactionLogAspect {

    /**
     * Manages transaction logging
     * 
     * @param pjp
     * @param logTransaction
     * @return output - Object
     */
    @Around(value = "execution(public * *(..)) && @annotation(logTransaction)", argNames = "logTransaction")
    public Object profile(ProceedingJoinPoint pjp, LogTransaction logTransaction) throws Throwable {
        final Object output = pjp.proceed();
        return output;
    }
}