package com.example.demo5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo5.service.czhUserService;

/**
 * 应用启动时初始化数据
 */
@Component
public class czhDataInitializer implements CommandLineRunner {

    @Autowired
    private czhUserService czhUserService;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
    }

    private void initializeUsers() {
        try {
            // 检查并创建管理员账户
            if (czhUserService.findByUserName("admin") == null) {
                // 直接创建管理员账户，需要特殊处理角色
                createAdminAccount();
                System.out.println("创建管理员账户成功: admin/admin123");
            }

            // 检查并创建测试用户账户
            if (czhUserService.findByUserName("user") == null) {
                czhUserService.registerUser("user", "user123", "user@example.com");
                System.out.println("创建测试用户账户成功: user/user123");
            }

        } catch (Exception e) {
            System.err.println("初始化用户数据失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createAdminAccount() {
        czhUserService.createAdminUser("admin", "admin123", "admin@example.com");
    }
}
