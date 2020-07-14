package com.seek.stocks.controller;


import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.httpclient.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("index")
public class IndexController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${juhe.url}")
    private String juheUrl;

    @Value("${juhe.appkey}")
    private String appkey;

    @GetMapping("/getStock")
    @ResponseBody
    public JSONObject index(@RequestParam("gid") String gid,@RequestParam(value = "type",required = false) String type){
        JSONObject result = new JSONObject();

        HashMap<String,String> param = new HashMap<String, String>();

        param.put("gid",gid);
        param.put("type",type);
        param.put("key",appkey);
        try {
            result = HttpClientUtil.get(juheUrl, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
