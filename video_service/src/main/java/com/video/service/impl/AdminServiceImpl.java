package com.video.service.impl;

import com.video.dao.AdminMapper;
import com.video.pojo.Admin;
import com.video.pojo.AdminExample;
import com.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyuf
 * @date 2020/10/19 15:25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findUserByNameAndPsw(String username, String password) {

        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        adminExample.createCriteria().andPasswordEqualTo(password);

        return (Admin) adminMapper.selectByExample(adminExample);
    }
}
