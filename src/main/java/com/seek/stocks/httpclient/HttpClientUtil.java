package com.seek.stocks.httpclient;


import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil  {


    public static void get(String url , Map<String,String> param) throws IOException {
        JSONObject result = new JSONObject();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String fullUrl = setUrl(url,param);
        HttpGet httpGet = new HttpGet(fullUrl);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();

        httpGet.setConfig(requestConfig);
        response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if(Response.SC_OK != statusCode){
            result.put("code",statusCode);
        }

        HttpEntity entity = response.getEntity();
        System.out.println("响应码:"+statusCode);
        System.out.println("响应内容长度为:" + entity.getContentLength());
        System.out.println("响应内容为:" + EntityUtils.toString(entity));

    }

    private static String setUrl(String url,Map<String,String> param) {
        if(param == null || param.size() <= 0 ){
            return "";
        }

        Set<Map.Entry<String, String>> entrySet = param.entrySet();
        StringBuffer paramUrl = new StringBuffer(url);
        paramUrl.append("?");
        for (Map.Entry<String, String> entry : entrySet) {
            paramUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        return paramUrl.toString().substring(0,paramUrl.toString().lastIndexOf("&"));
    }

}
