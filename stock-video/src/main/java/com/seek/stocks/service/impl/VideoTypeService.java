package com.seek.stocks.service.impl;

import com.seek.stocks.dao.VideoTypeMapper;
import com.seek.stocks.model.VideoType;
import com.seek.stocks.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTypeService implements IVideoTypeService {

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Override
    public Integer addVideoType(VideoType videoType) {
        return videoTypeMapper.insertSelective(videoType);
    }

    @Override
    public Integer updateVideoType(VideoType videoType) {
        return videoTypeMapper.updateByPrimaryKeySelective(videoType);
    }

    @Override
    public Integer deleteVideoType(Integer id) {
        return videoTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<VideoType> queryAllVideoType() {
        return videoTypeMapper.queryAllVideoType();
    }
}
