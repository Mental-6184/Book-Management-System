package com.example.demo5.interceptor;

import com.example.demo5.entity.czhUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理员权限拦截器
 */
public class czhAdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        
        // 检查是否为管理员
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            response.sendRedirect("/user/login");
            return false;
        }
        
        return true;
    }
}
