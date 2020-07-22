package com.seek.stocks.controller;

import com.seek.stocks.annotation.ApiRateLimit;
import com.seek.stocks.aop.RequestAop;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *  ApplicationContextAware实现类可以获得spring上下文
 *   间接获取ApplicationContext中的所有bean,向切面添加所有接口的配置的限流量
 */
@Component
public class IintApiLimit implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RestController.class);
        beanMap.forEach((k,v)->{
            Class<? extends Object> controllerClass = v.getClass();
            //获取所有的声明方法

            Method[] methods = controllerClass.getSuperclass().getDeclaredMethods();
            for (Method method : methods){
                //判断是否使用了限流注解
                if (method.isAnnotationPresent(ApiRateLimit.class)){
                    //获取配置的限流量,实际值可以动态获取,配置key,根据key从配置文件获取
                    int value = method.getAnnotation(ApiRateLimit.class).value();
                    String key = String.valueOf(value);
                    //key作为key.value为具体限流量,传递到切面的map中
                    RequestAop.semaphoreMap.put(key,new Semaphore(value));
                }
            }
        });
    }
}
