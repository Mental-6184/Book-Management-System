package com.example.demo5.config;

import com.example.demo5.interceptor.czhAdminInterceptor;
import com.example.demo5.interceptor.czhLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 */
@Configuration
public class czhWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new czhLoginInterceptor())
                .addPathPatterns("/user/home", "/book/**")
                .excludePathPatterns("/user/login", "/user/register");
        
        // 管理员权限拦截器
        registry.addInterceptor(new czhAdminInterceptor())
                .addPathPatterns("/admin/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置文件上传访问路径
        registry.addResourceHandler("/czh-upload/**")
                .addResourceLocations("file:d:/czh-upload/");
    }
}
