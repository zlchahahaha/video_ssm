package com.video.service;

import com.video.pojo.QueryVo;
import com.video.pojo.Video;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 19:53
 */
public interface VideoService {
    List<Video> findAllVideo(QueryVo queryVo);
}
