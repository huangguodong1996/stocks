package com.seek.stocks.service;

import com.seek.stocks.model.VideoType;

import java.util.List;

public interface IVideoTypeService {

    Integer addVideoType(VideoType videoType);

    Integer updateVideoType(VideoType videoType);

    Integer deleteVideoType(Integer id);

    List<VideoType> queryAllVideoType();
}
