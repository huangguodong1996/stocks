package com.seek.stocks.controller;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.model.VideoType;
import com.seek.stocks.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoTypeController {

    @Autowired
    private IVideoTypeService videoTypeService;

    @PostMapping("addVideoType")
    public String addVideoType(VideoType videoType){
        Integer successFlag = videoTypeService.addVideoType(videoType);
        return successFlag.toString();
    }

    @PostMapping("updateVideoType")
    public String updateVideoType(VideoType videoType){
        Integer successFlag = videoTypeService.updateVideoType(videoType);
        return successFlag.toString();
    }

    @GetMapping("deleteVideoType")
    public String deleteVideoType(@RequestParam("id") Integer id){
        Integer successFlag = videoTypeService.deleteVideoType(id);
        return successFlag.toString();
    }

    @GetMapping("/queryAllVideoType")
    public String  queryAllVideoType(){
        List<VideoType> videoTypeList = videoTypeService.queryAllVideoType();
        String jsonStr = JSONObject.toJSONString(videoTypeList);
        return jsonStr;
    }
}
