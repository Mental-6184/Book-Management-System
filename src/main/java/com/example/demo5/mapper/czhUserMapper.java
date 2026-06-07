package com.example.demo5.mapper;

import com.example.demo5.entity.czhUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 */
@Mapper
public interface czhUserMapper {
    
    /**
     * 根据用户名查询用户
     */
    czhUser findByUserName(@Param("czhUserName") String czhUserName);
    
    /**
     * 插入新用户
     */
    int insertUser(czhUser czhUser);
    
    /**
     * 根据用户名和密码查询用户（登录）
     */
    czhUser findByUserNameAndPassword(@Param("czhUserName") String czhUserName, 
                                      @Param("czhPassword") String czhPassword);
    
    /**
     * 更新用户信息
     */
    int updateUser(czhUser czhUser);
    
    /**
     * 根据ID删除用户
     */
    int deleteUser(@Param("czhUserId") Integer czhUserId);
}
