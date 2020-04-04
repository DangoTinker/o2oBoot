package dlnu.o2oboot.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import dlnu.o2oboot.util.ImageUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);


    @Pointcut("execution(public * dlnu.o2oboot.web.shopadmin..*.*(..))")
    public void pointCut(){}


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        logger.info("url="+request.getRequestURL());
        logger.info("method="+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        System.out.println("url="+request.getRequestURL());
        System.out.println("method="+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    }


//    @AfterReturning(value = "pointCut()",returning="returnValue")
//    public void s( Object returnValue){
//        System.out.println("ttttttttt");
//        System.out.println(returnValue.toString());
//        System.out.println("ttttttttt");
//
//    }
//
//    @Pointcut("execution(* dlnu.o2oboot.util..*(..))")
//    public void shortUrlPointcut(){}
//
//    @Before("shortUrlPointcut()")
//    public void tt(){
//        System.out.println("zzzzzzzzz");
//    }
//
//    @AfterReturning(value = "shortUrlPointcut()",returning="returnValue")
//    public void after(JoinPoint joinPoint , Object returnValue){
//
//        logger.info("method="+joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());
//        logger.info("return="+returnValue.toString());
//        System.out.println(returnValue);
//
//    }


}
