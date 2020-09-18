package com.seek.stocks.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.constant.Constants;
import com.seek.stocks.dao.VideoMapper;
import com.seek.stocks.model.Video;
import com.seek.stocks.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public String addVideo(Video video) {
        JSONObject resultJson = new JSONObject();
        int i = videoMapper.insertSelective(video);
        if (i > 0) {
            resultJson.put(Constants.RES_CODE,Constants.RES_CODE_SUCCESS);
            resultJson.put(Constants.RES_SHOW,Constants.RES_SHOW_SUCCESS);
        }else{
            resultJson.put(Constants.RES_CODE,Constants.RES_CODE_FAIL);
            resultJson.put(Constants.RES_SHOW,Constants.RES_SHOW_FAIL);
        }
        return resultJson.toJSONString();
    }

    @Override
    public String updateVideo(Video video) {
        JSONObject resultJson = new JSONObject();
        int i = videoMapper.updateByPrimaryKeySelective(video);
        if (i > 0) {
            resultJson.put(Constants.RES_CODE,Constants.RES_CODE_SUCCESS);
            resultJson.put(Constants.RES_SHOW,Constants.RES_SHOW_SUCCESS);
        }else{
            resultJson.put(Constants.RES_CODE,Constants.RES_CODE_FAIL);
            resultJson.put(Constants.RES_SHOW,Constants.RES_SHOW_FAIL);
        }
        return resultJson.toJSONString();
    }

    @Override
    public Integer deleteVideo(Integer id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Video> queryAllVideo() {
        return videoMapper.queryAllVideo();
    }
}
