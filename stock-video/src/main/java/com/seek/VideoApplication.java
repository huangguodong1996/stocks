package com.seek;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@MapperScan("com.seek.stocks.dao")
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}
