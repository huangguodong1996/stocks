package com.seek.stocks.dao;

import com.seek.stocks.model.VideoType;

import java.util.List;

public interface VideoTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(VideoType record);

    int insertSelective(VideoType record);

    VideoType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(VideoType record);

    int updateByPrimaryKey(VideoType record);

    List<VideoType> queryAllVideoType();

}
