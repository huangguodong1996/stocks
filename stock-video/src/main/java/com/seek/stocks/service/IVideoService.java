package com.seek.stocks.service;

import com.seek.stocks.model.Video;

import java.util.List;

public interface IVideoService {

    String addVideo(Video video);

    String updateVideo(Video video);

    Integer deleteVideo(Integer id);

    List<Video> queryAllVideo();
}
