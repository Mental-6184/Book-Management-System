package com.example.demo5.controller;

import com.example.demo5.entity.czhBook;
import com.example.demo5.entity.czhUser;
import com.example.demo5.service.czhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 图书查询控制器（普通用户）
 */
@Controller
@RequestMapping("/book")
public class czhBookController {

    @Autowired
    private czhBookService czhBookService;

    /**
     * 显示单条件查询页面
     */
    @GetMapping("/search-single")
    public String showSingleSearchPage(HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("czhUser", czhCurrentUser);
        return "czh-book-search-single";
    }

    /**
     * 单条件查询处理
     */
    @PostMapping("/search-single")
    public String singleSearch(@RequestParam("czhSearchType") String czhSearchType,
                              @RequestParam("czhSearchValue") String czhSearchValue,
                              HttpSession session,
                              Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }

        List<czhBook> czhBookList = null;
        
        switch (czhSearchType) {
            case "bookName":
                czhBookList = czhBookService.findByBookName(czhSearchValue);
                break;
            case "author":
                czhBookList = czhBookService.findByAuthor(czhSearchValue);
                break;
            case "isbn":
                czhBookList = czhBookService.findByIsbn(czhSearchValue);
                break;
            default:
                czhBookList = czhBookService.findAllBooks();
        }

        model.addAttribute("czhUser", czhCurrentUser);
        model.addAttribute("czhBookList", czhBookList);
        model.addAttribute("czhSearchType", czhSearchType);
        model.addAttribute("czhSearchValue", czhSearchValue);
        return "czh-book-search-single";
    }

    /**
     * 显示多条件查询页面
     */
    @GetMapping("/search-multi")
    public String showMultiSearchPage(HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("czhUser", czhCurrentUser);
        return "czh-book-search-multi";
    }

    /**
     * 多条件查询处理
     */
    @PostMapping("/search-multi")
    public String multiSearch(@RequestParam(value = "czhBookName", required = false) String czhBookName,
                             @RequestParam(value = "czhAuthor", required = false) String czhAuthor,
                             @RequestParam(value = "czhIsbn", required = false) String czhIsbn,
                             @RequestParam(value = "czhCategory", required = false) String czhCategory,
                             HttpSession session,
                             Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null) {
            return "redirect:/user/login";
        }

        List<czhBook> czhBookList = czhBookService.findByMultiConditions(
            czhBookName, czhAuthor, czhIsbn, czhCategory);

        model.addAttribute("czhUser", czhCurrentUser);
        model.addAttribute("czhBookList", czhBookList);
        model.addAttribute("czhBookName", czhBookName);
        model.addAttribute("czhAuthor", czhAuthor);
        model.addAttribute("czhIsbn", czhIsbn);
        model.addAttribute("czhCategory", czhCategory);
        return "czh-book-search-multi";
    }
}
