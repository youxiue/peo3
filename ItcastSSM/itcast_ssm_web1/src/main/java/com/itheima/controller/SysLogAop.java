package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class SysLogAop {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;

    private Date visitTime; //开始时间
    private Class clazz;//访问对象
    private Method method;//访问的方法

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void f1(){}

    @Before("f1();")
    public void doBefore(){
        //获取开始时间
        visitTime = new Date();
    }

    //获取 时长 ip URL method username
    @After("f1();")
    public void doAfter(JoinPoint jp) throws  Exception{
        //获取时长
        long executionTime = new Date().getTime() - visitTime.getTime();

        //要获取访问的URL  必须要获取对应的类和方法的字节码对象  然后 用字节码对象获取注解中的路径
        //1.获取类对象
        clazz = jp.getTarget().getClass();
        //2.获取方法对象
        //2.1 要获取方法对象   需要先获取方法对象名
        String methodName = jp.getSignature().getName();
        //2.2 还需要获取该方法的参数对象  才能准确找到该方法
        Object[] args = jp.getArgs();
        //2.3 判断是否有参数
        if(args!=null && args.length>0){
            //2.4 将参数 对象转成字节码对象 先创建一个字节码 数组 用来接收
            Class[] argsClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //对于 Model 对象 需要做特殊处理   其  获取参数后  为 ModelMap  所以 需要将该参数设置为Model.class
                if(args[i] instanceof BindingAwareModelMap){
                    argsClass[i] = Model.class;
                }else{
                    argsClass[i] = args[i].getClass();
                }
            }
            method = clazz.getMethod(methodName,argsClass);
        }else {
            method = clazz.getMethod(methodName);
        }
        //3.获取注解中的路径
        //3.1 先对 类对象 方法对象 做健壮性判断    同时放过日志访问
        if(clazz!=null && method!=null && clazz!=SysLogController.class){
            //3.2 获取类对象上的注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation!=null){
                String[] clazzValue = clazzAnnotation.value();
                //3.3 获取方法上的注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    String url = clazzValue[0]+methodValue[0];


                    //获取 ip
                    String ip = request.getRemoteAddr();
                    //获取访问用户名
                    SecurityContext context = SecurityContextHolder.getContext();
                    User principal = (User) context.getAuthentication().getPrincipal();
                    String username = principal.getUsername();

                    //将数据存到对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUrl(url);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    //将对象保存到数据库
                    sysLogService.save(sysLog);

                }
            }
        }
    }









/*
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        System.out.println("前置方法");
        //获取开始时间
        visitTime = new Date();

    }

    //后置通知  获取访问ip 访问路径 访问时长 操作者用户名 访问方法
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        System.out.println("后置方法");
        //访问时长
        long execution = new Date().getTime() - visitTime.getTime();


        //获取对象
        clazz = jp.getTarget().getClass();
        //获取访问的方法
        //1.获取访问方法名
        String methodName = jp.getSignature().getName();
        //2.获取方法的参数
        Object[] args = jp.getArgs();
        if (args != null && args.length > 0) {
            //有参数
            Class[] argsClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //因为 model 有参数之后  为 modelMap  所以ModelMap.class 与 该方法的参数Model.class不匹配
                //就会找不到  报错   所以 需要对model参数进行处理  属于BindingAwareModelMap   手动设置其为Model.class
                if(args[i] instanceof BindingAwareModelMap){
                    argsClass[i] = Model.class;
                }else{
                    argsClass[i] = args[i].getClass();
                }
            }
            method = clazz.getMethod(methodName, argsClass);
        } else {
            //没有参数
            method = clazz.getMethod(methodName);
        }

        //访问路径
        //1.先获取访问类的路径
        String url = null;
        if(clazz!=null && method!=null && clazz!=SysLogAop.class) {
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] clazzValue = clazzAnnotation.value();
                //2.获取访问方法的路径
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = clazzValue[0] + methodValue[0];

                    //访问ip
                    String ip = request.getRemoteAddr();

                    //获取当前访问的对象
                    SecurityContext context = SecurityContextHolder.getContext();
                    User users = (User) context.getAuthentication().getPrincipal();
                    String username = users.getUsername();

                    //将数据封装到对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setExecutionTime(execution);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名] " + clazz.getName() + " [方法名] " + method.getName());

                    System.out.println(sysLog);
                    //调用方法将数据存到数据库中
                    sysLogService.save(sysLog);
                }
            }
        }
    }*/




}
