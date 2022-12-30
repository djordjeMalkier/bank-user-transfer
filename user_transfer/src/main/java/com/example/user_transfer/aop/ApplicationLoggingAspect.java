package com.example.user_transfer.aop;


import com.example.user_transfer.model.ApplicationLog;
import com.example.user_transfer.repository.MyEntityRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Aspect
@Builder
@Component
@Slf4j

public class ApplicationLoggingAspect {

 private final MyEntityRepository myEntityRepository;

    @Pointcut("execution(* com.example.user_transfer.controller.UserTransferController.transferFromUserAccountToAnotherUserAccount(..))")
    public void pointcut(){

    }
    @SuppressWarnings("unchecked cast")
    @Around("pointcut()")
    public ResponseEntity<String> after(ProceedingJoinPoint joinPoint) throws Throwable {
        ApplicationLog request=new ApplicationLog();
        String methodName=joinPoint.getSignature().getName();
        ResponseEntity<String> payment = (ResponseEntity<String>) joinPoint.proceed();
        request.setPayment(payment.getBody());
        request.setMethod(methodName);
        request.setCreatedTime(LocalDateTime.now());
        myEntityRepository.save(request);


        return payment;
    }
}
