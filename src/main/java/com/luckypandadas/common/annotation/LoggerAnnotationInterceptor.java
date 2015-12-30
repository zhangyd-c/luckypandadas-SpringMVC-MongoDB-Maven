package com.luckypandadas.common.annotation;

import com.luckypandadas.common.ResponseVo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas
 * User：yadong.zhang
 * Date：2015/12/30
 * Time：14:33
 */
//这个类为切面类
@Aspect
//作为切面类需要 Spring 管理起来，所以在初始化时就需要将这个类初始化加入 Spring 的管理
@Component
public class LoggerAnnotationInterceptor {
    /**
     * 日志变量
     */
    private static final Logger LOGGER = Logger.getLogger(LoggerAnnotationInterceptor.class);

    /**
     * 分隔符，根据level值进行排版，controller层level值为1，service层level值为2
     */
    private static String[] SPACES = {"----", "--------", "------------", "----------------"};

    /**
     * 某个类的某个方法的开始
     */
    private static final String ACTIONENTER = "%s%s进入类：%s 方法：%s";

    private static final String RETURNPARAM = "%s%s进入类：%s 方法：%s 返回值：%s";

    /**
     * 某个类的某个方法的结束
     */
    private static final String ACTIONCONSUME = "%s%s离开类：%s 方法：%s 消耗时间：%s ms";


    private static long startTime = 0;
    private static long endTime = 0;

    /**
     * @Description 定义切入点
     * @author liuy-8
     * @date 2015年1月12日 上午10:27:41
     */
    @Pointcut(value = "@annotation(com.luckypandadas.common.annotation.LoggerAnnotation)")
    public void logAnnotatedMethods() {
    }

    @Around("logAnnotatedMethods()")
    public Object interceptorAction(ProceedingJoinPoint pjp) throws Throwable {
        startTime = System.currentTimeMillis();
        System.err.println("Around通知  开始");
        Object o = null;
        String loginName = "";
//        Object[] parames = pjp.getArgs();//获取请求参数
//        for (Object parame : parames) {
//            if (parame instanceof HttpServletRequest) {
//                HttpServletRequest request = (HttpServletRequest) parame;
//            }
//        }

        LoggerAnnotation annotation = getLoggerAnnotation(pjp);
        LOGGER.debug(String.format(ACTIONENTER, SPACES[annotation.level() - 1], "zhangyad", annotation.className(), annotation.methodName()));

        if(annotation != null){
            //用于执行委托对象的目标方法
            o = pjp.proceed();
        }

        if (o == null){
            o = pjp.proceed();
        }
        //RETURNPARAM
        ResponseVo rv = (ResponseVo)o ;
        LOGGER.debug(String.format(RETURNPARAM, SPACES[annotation.level() - 1],  "",annotation.className(), annotation.methodName(), JSONObject.fromObject(rv).toString()));
        endTime = System.currentTimeMillis();
        LOGGER.debug(String.format(ACTIONCONSUME, SPACES[annotation.level() - 1], "zhangyad", annotation.className(), annotation.methodName(), endTime - startTime));
        System.err.println("Around通知  结束");
        return o;
    }

    /**
     * 对应 Target 执行之前
     */
    @Before("logAnnotatedMethods()")
    public void before() {
        System.err.println("(before)方法执行...");
    }

    /**
     * 对应 target 执行之后
     */
    @After("logAnnotatedMethods()")
    public void after() {
        System.err.println("(after)离开方法...");
    }


    @AfterReturning("logAnnotatedMethods()")
    public void AfterReturning(JoinPoint jp) {
        Signature signature = jp.getSignature();
//        LOGGER.debug("DeclaringType:" + signature.getDeclaringType());
//        LOGGER.debug("DeclaringTypeName:" + signature.getDeclaringTypeName());
//        LOGGER.debug("Modifiers:" + signature.getModifiers());
//        LOGGER.debug("Name:" + signature.getName());
        LOGGER.debug(signature.toLongString() + "方法成功返回：");
//        LOGGER.debug("ShortString:" + signature.toShortString());

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jp.getArgs().length; i++) {
            Object arg = jp.getArgs()[i];
            if(null != arg) {
                sb.append(arg.toString());
            }
        }
        LOGGER.debug("返回参数：" + sb.toString());
    }

    @AfterThrowing(pointcut="logAnnotatedMethods()",throwing="e")
    public void AfterThrowing(Exception e) {
        System.err.println("发生异常..." + e);
    }

    private LoggerAnnotation getLoggerAnnotation(ProceedingJoinPoint pjp){
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(LoggerAnnotation.class);
        if (flag) {
            return method.getAnnotation(LoggerAnnotation.class);
        }
        return null;
    }
}
