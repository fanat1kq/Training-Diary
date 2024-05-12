package org.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.model.User;

@Aspect
public class AuditAspect {
    @Before("@annotation(org.example.aop.annotations.Audit)")
    public void logMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        switch (methodName) {
            case "authorization", "register" -> {
                User user = (User) args[0];
                System.out.println("User " + user + " try to " + methodName);
            }
            case "getTraining" -> {
                int id = (int) args[0];
                System.out.println("User with id " + id + " try to look his training");
            }
            case "addTraining" -> {
                int id = (int) args[1];
                System.out.println("User with id " + id + " try to add his training");
            }
            case "deleteTraining" -> {
                int id = (int) args[1];
                System.out.println("User try to delete his training with id " + id);
            }
            case "getPlayerHistory" -> {
                int id = (int) args[0];
                System.out.println("Player with id " + id + " try to look his transactions history");
            }
        }
    }
}
