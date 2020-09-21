package com.seek.stocks.controller;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.constant.Constants;
import com.seek.stocks.model.UmsAdmin;
import com.seek.stocks.service.IUmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private IUmsAdminService umsAdminService;

    private Logger log = LoggerFactory.getLogger(UmsAdminController.class);

    @PostMapping("/register")
    public String register (UmsAdmin umsAdmin){
        return umsAdminService.register(umsAdmin);
    }

    @RequestMapping("/adminHello")
    public String adminHello(){
        return "hello";
    }
}
