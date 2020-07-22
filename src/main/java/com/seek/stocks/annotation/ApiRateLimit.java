package com.seek.stocks.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD) //作用在方法上
@Retention(RetentionPolicy.RUNTIME) //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Documented
public @interface ApiRateLimit {

    int value(); //最大并发数
}
