package Application.ExceptionHandlers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(Application..*)")
    public void wholePackage() {}

    @Around("wholePackage()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Exception caught in around advice: " + e.getMessage());
            return "error"; // Or handle it differently
        } finally {
            long executionTime = System.currentTimeMillis() - start;
            System.out.println(joinPoint.getSignature().getName() + "() executed in " + executionTime + "ms");
        }
    }

    @AfterReturning(pointcut = "wholePackage()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning from Method: " + joinPoint.getSignature().getName() + "()");
        System.out.println("Returned Value: " + result);
    }

    @AfterThrowing(pointcut = "wholePackage()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("After Throwing Exception in Method: " + joinPoint.getSignature().getName() + "()");
        System.out.println("Exception: " + error.getMessage());
    }
}