package com.video.service.impl;

import com.video.dao.SpeakerMapper;
import com.video.pojo.Speaker;
import com.video.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:46
 */
@Service
public class SperkerServiceImpl implements SpeakerService {
    @Autowired
    private SpeakerMapper speakerMapper;

    @Override
    public List<Speaker> findAll() {
        return speakerMapper.selectByExample(null);
    }
}
