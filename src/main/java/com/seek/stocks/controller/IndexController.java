package com.seek.stocks.controller;


import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.httpclient.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("index")
public class IndexController {

    @RequestMapping("/getStock")
    @ResponseBody
    public JSONObject index(HttpServletRequest request){
        JSONObject result = new JSONObject();

        String url = "http://quotes.money.163.com/service/chddata.html";
        HashMap<String,String> param = new HashMap<String, String>();
        param.put("code","0600072");
        try {
            HttpClientUtil.get(url,param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
