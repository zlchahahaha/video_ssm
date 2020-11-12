package com.video.service;

import com.video.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User loginUser(User user);

    public User findUserById(Integer account);

    public void updateUser(User user);

    public User findUserByEmail(String email);

    public void insertUser(User user);

}
