package com.example.demo5.service;

import com.example.demo5.entity.czhUser;
import com.example.demo5.mapper.czhUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 */
@Service
public class czhUserService {

    @Autowired
    private czhUserMapper czhUserMapper;

    /**
     * 用户注册
     */
    public boolean registerUser(String czhUserName, String czhPassword, String czhEmail) {
        // 检查用户名是否已存在
        czhUser czhExistingUser = czhUserMapper.findByUserName(czhUserName);
        if (czhExistingUser != null) {
            return false; // 用户名已存在
        }
        
        // 创建新用户，默认角色为 czh_user
        czhUser czhNewUser = new czhUser(czhUserName, czhPassword, czhEmail, "czh_user");
        int czhResult = czhUserMapper.insertUser(czhNewUser);
        return czhResult > 0;
    }

    /**
     * 用户登录
     */
    public czhUser loginUser(String czhUserName, String czhPassword) {
        return czhUserMapper.findByUserNameAndPassword(czhUserName, czhPassword);
    }

    /**
     * 根据用户名查询用户
     */
    public czhUser findByUserName(String czhUserName) {
        return czhUserMapper.findByUserName(czhUserName);
    }

    /**
     * 更新用户信息
     */
    public boolean updateUser(czhUser czhUser) {
        return czhUserMapper.updateUser(czhUser) > 0;
    }

    /**
     * 创建管理员账户
     */
    public boolean createAdminUser(String czhUserName, String czhPassword, String czhEmail) {
        // 检查用户名是否已存在
        czhUser czhExistingUser = czhUserMapper.findByUserName(czhUserName);
        if (czhExistingUser != null) {
            return false; // 用户名已存在
        }
        
        // 创建新管理员用户，角色为 czh_admin
        czhUser czhNewUser = new czhUser(czhUserName, czhPassword, czhEmail, "czh_admin");
        int czhResult = czhUserMapper.insertUser(czhNewUser);
        return czhResult > 0;
    }

    /**
     * 删除用户
     */
    public boolean deleteUser(Integer czhUserId) {
        return czhUserMapper.deleteUser(czhUserId) > 0;
    }
}
