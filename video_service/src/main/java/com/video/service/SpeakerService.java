package com.video.service;

import com.video.pojo.Speaker;
import com.video.pojo.Video;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    Speaker findById(Integer id);

    void addSpeaker(Speaker speaker);

    void updateSpaker(Speaker speaker);

    void speakerDel(Integer id);
}
