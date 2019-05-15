package com.aaxon.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ElvisWu
 * @version: 1.0, May 13, 2019
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
	private long startTime = 0L;
	private long endTime   = 0L;

	@Before("execution(* *..controller..*.*(..))")
	public void doBeforeController(JoinPoint pJoinPoint) {
		startTime = System.currentTimeMillis();
		log.info("start at: {}", startTime);
	}

	@After("execution(* *..controller..*.*(..))")
	public void doAfterController(JoinPoint pJoinPoint) {
		endTime = System.currentTimeMillis();
		log.info("end at: {}", endTime);
	}

	@Around("execution(* *..controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pProceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object result = pProceedingJoinPoint.proceed();
		return result;
	}
}
