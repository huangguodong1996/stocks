package com.seek.stocks.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUploadService {

    /**
     * 视频文件上传
     * @param request
     * @return
     */
    public JSONObject uploadVideo(HttpServletRequest request,MultipartFile file) ;

    /**
     * 图片文件上传
     * @param request
     * @return
     */
    public JSONObject uploadImage(HttpServletRequest request,MultipartFile file) ;

}
