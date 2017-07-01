package  com.techmahindra.smartparking.common.aspects.logger.tracer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.techmahindra.smartparking.pojo.dto.AbstractBaseDTO;

/**
 * TraceLoggerAspect.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Aspect
@Component
public class TraceLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(TraceLoggerAspect.class); // Initializing
                                                                                           // logger
    private static final String CONST_EXEC_START = "Execution started for method ";
    private static final String CONST_EXEC_END = "Execution ended for method ";
    private static final String CONST_IN = " in ";
    private static final String CONST_USER = " for user ";
    private static final String CONST_TIME = " at time ";
    private final ThreadLocal<String> userPrincipal = new ThreadLocal<>();

    /**
     * Executed before the start of the method
     * 
     * @param joinPoint
     */
    @Before("execution(public * com.techmahindra.smartparking..*.*(..))")
    public void beforeEntryTracer(final JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            String user = null;
            final Class<? extends Object> targetClass = joinPoint.getTarget().getClass();
            final String targetMethod = joinPoint.getSignature().toLongString();
            final StringBuilder builder = createLogMessage(targetClass, targetMethod, user, true);
            logger.debug(builder.toString());
        }
    }

    /**
     * Executed after the method returns
     * 
     * @param joinPoint
     */
    @AfterReturning("execution(public * com.techmahindra.smartparking..*.*(..))")
    public void afterExitTracer(final JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            final String user = userPrincipal.get();
            final Class<? extends Object> targetClass = joinPoint.getTarget().getClass();
            final String targetMethod = joinPoint.getSignature().toLongString();
            final StringBuilder builder = createLogMessage(targetClass, targetMethod, user, false);
            logger.debug(builder.toString());
        }
    }

    /**
     * This method creates log messages
     * 
     * @param targetClass
     * @param targetMethod
     * @param user
     * @param start
     * @return builder - StringBuilder
     */
    private StringBuilder createLogMessage(final Class<? extends Object> targetClass,
            final String targetMethod, final String user, final boolean start) {
        final StringBuilder builder = new StringBuilder();
        builder.append(start ? CONST_EXEC_START : CONST_EXEC_END);
        builder.append(targetMethod);
        builder.append(CONST_IN);
        builder.append(targetClass);
        final String userInfo = user != null ? CONST_USER + user : "";
        builder.append(userInfo);
        builder.append(CONST_TIME);
        builder.append(System.currentTimeMillis());
        return builder;
    }

    /**
     * Executed before the start of the method
     * 
     * @param joinPoint
     */
    @Before("execution(public * com.techmahindra.smartparking.controller..*.*(..))")
    public void beforeEntryControllerTracer(final JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {

            String user = null;
            final Class<? extends Object> targetClass = joinPoint.getTarget().getClass();
            final String targetMethod = joinPoint.getSignature().toLongString();
            Object[] args = joinPoint.getArgs();
            for (Object params : args) {
                if (AbstractBaseDTO.class.isInstance(params)) {
                    logger.debug(params.toString() + " -> " + params.toString());
                }
            }

            final StringBuilder builder = createLogMessage(targetClass, targetMethod, user, true);
            logger.debug(builder.toString());
        }
    }
}