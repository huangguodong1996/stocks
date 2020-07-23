package com.seek.stocks.aop;

import com.seek.stocks.annotation.ApiRateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 请求切面
 */
@Component
@Aspect
public class RequestAop {

    private Semaphore semaphore = new Semaphore(2);

    public static HashMap<String,Semaphore> semaphoreMap = new HashMap<String, Semaphore>();
    /**
     * 切点
     */
    @Pointcut("execution(public * com.seek.stocks.controller.IndexController.*(..))")
    public void pointcut(){
        System.out.println("切点");
    }

    @Before("pointcut()")
    public void before(){

    }

    @After("pointcut()")
    public  void after(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){Object result=null;
        Semaphore semaphore=null;
        Class<?> clz = joinPoint.getTarget().getClass();//获取目标对象
        Signature signature = joinPoint.getSignature();//获取增强方法信息
        String name = signature.getName();
        String limitKey = String.valueOf(getLimitKey(clz, name));
        try {
            if(limitKey!=null && !"".equals(limitKey) && !"-1".equals(limitKey)){
                semaphore = semaphoreMap.get(limitKey);
                semaphore.acquire();
            }
            System.out.println(Thread.currentThread().getName()+":进入查询");
            result=joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if(limitKey!=null && !"".equals(limitKey) && !"-1".equals(limitKey)){
                semaphore.release();
            }
        }
        return result;

    }

    //获取拦截方法配置的限流key,没有返回null
    private Integer getLimitKey(Class<?> clz, String methodName){
        for (Method method:clz.getDeclaredMethods()){
            if(method.getName().equals(methodName)){//找出目标方法
                if(method.isAnnotationPresent(ApiRateLimit.class)){//判断是否是限流方法
                    return method.getAnnotation(ApiRateLimit.class).value();
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        //信号量，只允许 3个线程同时访问
//        Semaphore semaphore = new Semaphore(3);
//
//        for (int i=0;i<10;i++){
//            final long num = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        //获取许可
//                        semaphore.acquire();
//                        //执行
//                        System.out.println("Accessing: " + num);
//                        Thread.sleep(new Random().nextInt(5000)); // 模拟随机执行时长
//                        //释放
//                        semaphore.release();
//                        System.out.println("Release..." + num);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        executorService.shutdown();
        for (int i=0;i<10;i++){
            new Thread(()->{
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForObject("http://127.0.0.1:8080/index/getStockwy?gid=002129",String.class);
            }).start();
        }

    }

}
