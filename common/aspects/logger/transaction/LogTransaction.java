package com.techmahindra.smartparking.common.aspects.logger.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LogTransaction.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface LogTransaction {
    int transactionCode();
}