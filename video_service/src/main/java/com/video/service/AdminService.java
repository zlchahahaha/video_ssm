package com.video.service;

import com.video.pojo.Admin;

/**
 * @author lyuf
 * @date 2020/10/19 15:24
 */
public interface AdminService {

    Admin findUserByNameAndPsw(String username, String password);
}
