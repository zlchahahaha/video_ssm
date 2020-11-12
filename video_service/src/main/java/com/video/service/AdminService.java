package com.video.service;

import com.video.pojo.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> findUserByNameAndPsw(String username, String password);
}
