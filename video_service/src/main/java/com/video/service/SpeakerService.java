package com.video.service;

import com.video.pojo.Speaker;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:46
 */
public interface SpeakerService {
    List<Speaker> findAll();
}
