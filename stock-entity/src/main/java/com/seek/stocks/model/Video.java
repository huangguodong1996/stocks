package com.seek.stocks.model;

public class Video {
    private Integer videoId;

    private String videoName;

    private Integer videoType;

    private String videoSize;

    private String videoTimeSize;

    private String path;

    private String uploadMember;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoTimeSize() {
        return videoTimeSize;
    }

    public void setVideoTimeSize(String videoTimeSize) {
        this.videoTimeSize = videoTimeSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadMember() {
        return uploadMember;
    }

    public void setUploadMember(String uploadMember) {
        this.uploadMember = uploadMember;
    }
}