package sk.stopangin.spring.introduction.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;
import sk.stopangin.spring.introduction.service.CacheManager;

import java.util.Date;

@Component
@Aspect
public class LastModifiedAspect {

    @DeclareParents(value = "sk.stopangin.spring.introduction.service.CacheManager",
            defaultImpl = LastModifiedImpl.class)
    public static LastModified lastModified;

    @Before("execution(* sk.stopangin.spring.introduction.service.CacheManager.setData(..)) && this(bean) )")
//&& target(bean2) target je CacheManager, this je proxy
    public void logLastModified(JoinPoint joinPoint, LastModified bean) throws Throwable {
        System.out.println("setting last modified");
        bean.setLastModified(new Date());
    }
}

