package com.video.service;

import com.video.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author: Administrator
 * @date: 2020/10/19 19:20
 */
@Service
public interface UserService {
    public boolean loginUser(User user);
}
