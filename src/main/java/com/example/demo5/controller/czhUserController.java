package com.example.demo5.controller;

import com.example.demo5.entity.czhUser;
import com.example.demo5.entity.czhBook;
import com.example.demo5.service.czhUserService;
import com.example.demo5.service.czhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器（注册、登录）
 */
@Controller
@RequestMapping("/user")
public class czhUserController {

    @Autowired
    private czhUserService czhUserService;
    
    @Autowired
    private czhBookService czhBookService;

    /**
     * 显示登录页面
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "czh-login";
    }

    /**
     * 处理登录请求
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam("czhUserName") String czhUserName,
                           @RequestParam("czhPassword") String czhPassword,
                           @RequestParam(value = "czhRole", defaultValue = "czh_user") String czhRole,
                           HttpSession session,
                           Model model) {
        czhUser czhUser = czhUserService.loginUser(czhUserName, czhPassword);
        
        if (czhUser != null) {
            // 验证角色是否匹配
            if (czhRole.equals(czhUser.getCzhRole())) {
                // 登录成功，保存用户信息到session
                session.setAttribute("czhCurrentUser", czhUser);
                
                // 根据角色跳转到不同页面
                if ("czh_admin".equals(czhUser.getCzhRole())) {
                    return "redirect:/admin/home";
                } else {
                    return "redirect:/user/home";
                }
            } else {
                model.addAttribute("czhError", "用户身份不匹配");
                return "czh-login";
            }
        } else {
            model.addAttribute("czhError", "用户名或密码错误");
            return "czh-login";
        }
    }

    /**
     * 显示注册页面
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "czh-register";
    }

    /**
     * 处理注册请求
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam("czhUserName") String czhUserName,
                              @RequestParam("czhPassword") String czhPassword,
                              @RequestParam("czhEmail") String czhEmail,
                              Model model) {
        boolean czhSuccess = czhUserService.registerUser(czhUserName, czhPassword, czhEmail);
        
        if (czhSuccess) {
            model.addAttribute("czhSuccess", "注册成功，请登录");
            return "czh-login";
        } else {
            model.addAttribute("czhError", "用户名已存在");
            return "czh-register";
        }
    }

    /**
     * 用户首页
     */
    @GetMapping("/home")
    public String userHomePage(HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("czhUser", czhCurrentUser);
        return "czh-user-home";
    }

    /**
     * 显示所有图书
     */
    @GetMapping("/books")
    public String showAllBooks(HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }
        
        List<czhBook> czhBookList = czhBookService.findAllBooks();
        model.addAttribute("czhUser", czhCurrentUser);
        model.addAttribute("czhBookList", czhBookList);
        return "czh-user-books";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
