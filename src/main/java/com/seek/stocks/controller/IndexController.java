package com.seek.stocks.controller;


import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.annotation.ApiRateLimit;
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

    @Value("${wangyi.url}")
    private String wangyiUrl;

    @Value("${juhe.appkey}")
    private String appkey;

    @GetMapping("/getStockjh")
    @ResponseBody
    public JSONObject getStockjh(@RequestParam("gid") String gid,@RequestParam(value = "type",required = false) String type){
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

    @GetMapping("/getStockwy")
    @ResponseBody
    @ApiRateLimit(value = 2)
    public JSONObject getStockwy(@RequestParam("gid") String gid){
        System.out.println(Thread.currentThread().getName()+"进入getStockwy");
        JSONObject result = new JSONObject();

        HashMap<String,String> param = new HashMap<String, String>();
        gid=gid.startsWith("6")?"0".concat(gid):gid;
        gid=gid.startsWith("0")?"1".concat(gid):gid;

        param.put("gid",gid);
        try {
            result = HttpClientUtil.get(wangyiUrl, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(result.toJSONString());
        return result;
    }
}
