package com.seek.stocks.controller;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.model.Video;
import com.seek.stocks.service.IUploadService;
import com.seek.stocks.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class VideoController {



    @Autowired
    private IVideoService videoService;

    @Autowired
    private IUploadService uploadService;


    private Logger log = LoggerFactory.getLogger(VideoController.class);


    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("upload")
    public String upload(HttpServletRequest request, MultipartFile uploadFile){
        return uploadService.uploadVideo(request,uploadFile).toJSONString();
    }

    @PostMapping("addVideo")
    public String addVideo(Video video){
        return videoService.addVideo(video).toString();
    }


    @PostMapping("updateVideo")
    public String updateVideo(Video video){
        return videoService.updateVideo(video);
    }

    @GetMapping("deleteVideo")
    public String deleteVideo(@RequestParam("id") Integer id){
        Integer successFlag = videoService.deleteVideo(id);
        return successFlag.toString();
    }

    @GetMapping("/queryAllVideo")
    public String  queryAllVideo(){
        List<Video> videoList = videoService.queryAllVideo();
        String jsonStr = JSONObject.toJSONString(videoList);
        return jsonStr;
    }

}
