package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.model.Users;

@Aspect
public class AuditAspect {
    @Before("@annotation(org.example.aop.Audit)")
    public void logMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        switch (methodName) {
            case "authorization", "register" -> {
                Users users = (Users) args[0];
                System.out.println("User " + users + " try to " + methodName);
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
        }
    }
}