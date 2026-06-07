package com.example.demo5.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class czhGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // 记录异常信息（在实际应用中应该使用日志框架）
        e.printStackTrace();
        
        // 向用户显示友好的错误信息
        model.addAttribute("czhError", "系统发生错误：" + e.getMessage());
        return "czh-error"; // 返回错误页面
    }
    
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException e, Model model) {
        model.addAttribute("czhError", "数字格式错误，请检查价格输入");
        return "czh-error";
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("czhError", "参数错误：" + e.getMessage());
        return "czh-error";
    }
}
