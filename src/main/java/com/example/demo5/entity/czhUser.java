package com.example.demo5.entity;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
public class czhUser {
    private Integer czhUserId;
    private String czhUserName;
    private String czhPassword;
    private String czhEmail;
    private String czhRole;
    private LocalDateTime czhCreateTime;

    // 构造方法
    public czhUser() {
    }

    public czhUser(String czhUserName, String czhPassword, String czhEmail, String czhRole) {
        this.czhUserName = czhUserName;
        this.czhPassword = czhPassword;
        this.czhEmail = czhEmail;
        this.czhRole = czhRole;
    }

    // Getter 和 Setter 方法
    public Integer getCzhUserId() {
        return czhUserId;
    }

    public void setCzhUserId(Integer czhUserId) {
        this.czhUserId = czhUserId;
    }

    public String getCzhUserName() {
        return czhUserName;
    }

    public void setCzhUserName(String czhUserName) {
        this.czhUserName = czhUserName;
    }

    public String getCzhPassword() {
        return czhPassword;
    }

    public void setCzhPassword(String czhPassword) {
        this.czhPassword = czhPassword;
    }

    public String getCzhEmail() {
        return czhEmail;
    }

    public void setCzhEmail(String czhEmail) {
        this.czhEmail = czhEmail;
    }

    public String getCzhRole() {
        return czhRole;
    }

    public void setCzhRole(String czhRole) {
        this.czhRole = czhRole;
    }

    public LocalDateTime getCzhCreateTime() {
        return czhCreateTime;
    }

    public void setCzhCreateTime(LocalDateTime czhCreateTime) {
        this.czhCreateTime = czhCreateTime;
    }

    @Override
    public String toString() {
        return "czhUser{" +
                "czhUserId=" + czhUserId +
                ", czhUserName='" + czhUserName + '\'' +
                ", czhEmail='" + czhEmail + '\'' +
                ", czhRole='" + czhRole + '\'' +
                ", czhCreateTime=" + czhCreateTime +
                '}';
    }
}
