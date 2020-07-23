package com.seek.stocks.service;

import java.util.Arrays;

public class StartService {

    private String config;

    public StartService(String config){
        this.config = config;
    }

    public void split(){
        System.out.println(Arrays.toString(this.config.split(",")));
    }
}
