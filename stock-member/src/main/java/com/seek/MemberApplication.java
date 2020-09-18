package com.seek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seek.stocks.dao")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}
