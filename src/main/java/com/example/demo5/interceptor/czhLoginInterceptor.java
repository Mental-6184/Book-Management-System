package com.example.demo5.interceptor;

import com.example.demo5.entity.czhUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 */
public class czhLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        
        // 如果用户未登录，重定向到登录页面
        if (czhCurrentUser == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        
        return true;
    }
}
