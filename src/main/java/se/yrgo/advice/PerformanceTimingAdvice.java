package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice {
    public Object performTimingMeasurement (ProceedingJoinPoint method) throws Throwable
    {
        long timeStart = System.currentTimeMillis();
        try {
            Object value = method.proceed();
            return value;
        } finally {
            long timeStop = System.currentTimeMillis();
            long timeTaken = timeStop - timeStart;
            System.out.println("Time taken for the method " + method.getSignature().getName() + " from the " + method.getTarget().getClass() + " took " + timeTaken + "ms");
        }
    }
}
