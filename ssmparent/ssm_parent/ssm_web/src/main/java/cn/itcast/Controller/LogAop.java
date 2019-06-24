package cn.itcast.Controller;

import cn.itcast.ISyslogService;
import cn.itcast.domain.Syslog;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-23 15:45
 */
@Component//声明增强是配置类
@Aspect  //表示当前类是切面类
public class LogAop {

    private Date visitTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService iSyslogService;

    @Before("execution(* cn.itcast.Controller.*.*(..))&&!execution(* cn.itcast.Controller.SyslogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime=new Date();//访问时间

        executionClass = jp.getTarget().getClass();//获取访问的类

        String methodName = jp.getSignature().getName();//获取方法的名称

        Object[] args = jp.getArgs();//获取访问方法参数
        if(args.length==0||args==null) {
            executionMethod = executionClass.getMethod(methodName);//获取无参方法
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            executionMethod=executionClass.getMethod(methodName,classArgs);//获取有参方法
        }

    }

    @After("execution(* cn.itcast.Controller.*.*(..))&&!execution(* cn.itcast.Controller.SyslogController.*(..))")
        public void doAfter(JoinPoint jp)throws Exception{
        Syslog syslog=new Syslog();
        //获取url
        String url = request.getRequestURI();

        //获取访问时长
        Long executionTime=new Date().getTime()-visitTime.getTime();

        //获取IP
        String ip = request.getRemoteAddr();

        //获取用户名
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

        //封装Syslog对象
        syslog.setIp(ip);
        syslog.setUsername(username);
        syslog.setVisitTime(visitTime);
        syslog.setExecutionTime(executionTime);
        syslog.setMethod(executionClass.getName()+executionMethod.getName());
        syslog.setUrl(url);
        iSyslogService.save(syslog);
    }
}
