package com.video.service;

import com.video.pojo.QueryVo;
import com.video.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAllVideo(QueryVo queryVo);

    Video findById(Integer id);

    void updateVideo(Video video);

    void videoDel(Integer id);

    void addVideo(Video video);

    Video findVideoById(Integer videoId);
}
