package com.carlos.luke.commons.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(DaoInterceptor.class);

    @Around("interceptDao()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            Class clazz = MethodSignature.class.cast(joinPoint.getSignature()).getDeclaringType();
            DynamicDS targetDataSource = AnnotationUtils.findAnnotation(clazz, DynamicDS.class);
            if (targetDataSource != null) {
                DataSourceContextHolder.setTargetDataSource(DataSourceEnum.valueOf(targetDataSource.value()));
            } else {
                DataSourceContextHolder.resetDefaultDataSource();
            }
            result = joinPoint.proceed();
            return result;
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    @Pointcut("execution(* com.carlos.luke.dao..*.*(..))")
    public void interceptDao() {
    }
}
