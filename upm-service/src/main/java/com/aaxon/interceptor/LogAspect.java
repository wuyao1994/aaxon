package com.aaxon.interceptor;

import com.aaxon.domain.UpmLog;
import com.aaxon.service.UpmLogService;
import com.alibaba.fastjson.JSON;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UpmLogService mUpmLogService;

	@Before("execution(* *..controller..*.*(..))")
	public void doBeforeController(JoinPoint pJoinPoint) {
		startTime = System.currentTimeMillis();
		log.info("before controller");
	}

	@After("execution(* *..controller..*.*(..))")
	public void doAfterController(JoinPoint pJoinPoint) {
		log.info("after controller");
	}

	@Around("execution(* *..controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pProceedingJoinPoint) throws Throwable {
		UpmLog upmLog = new UpmLog();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object result = pProceedingJoinPoint.proceed();
		endTime = System.currentTimeMillis();
		upmLog.setStartTime(startTime);
		upmLog.setSpendTime((int)(endTime - startTime));
		upmLog.setResult(JSON.toJSONString(result));
		upmLog.setUri(request.getRequestURI());
		upmLog.setUrl(request.getRequestURL().toString());
		mUpmLogService.insetUpmLog(upmLog);
		return result;
	}
}
