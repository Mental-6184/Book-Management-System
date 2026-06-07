package com.example.demo5.controller;

import com.example.demo5.entity.czhBook;
import com.example.demo5.entity.czhUser;
import com.example.demo5.service.czhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 管理员控制器（图书管理）
 */
@Controller
@RequestMapping("/admin")
public class czhAdminController {

    @Autowired
    private czhBookService czhBookService;

    @Value("${czh.upload.path:d:/czh-upload/}")
    private String czhUploadPath;

    /**
     * 管理员首页
     */
    @GetMapping("/home")
    public String adminHomePage(HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }
        
        List<czhBook> czhBookList = czhBookService.findAllBooks();
        model.addAttribute("czhUser", czhCurrentUser);
        model.addAttribute("czhBookList", czhBookList);
        return "czh-admin-home";
    }

    /**
     * 显示添加图书页面
     */
    @GetMapping("/book/add")
    public String showAddBookPage(HttpSession session) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }
        return "czh-book-add";
    }

    /**
     * 处理添加图书请求
     */
    @PostMapping("/book/add")
    public String addBook(@RequestParam("czhBookName") String czhBookName,
                         @RequestParam("czhAuthor") String czhAuthor,
                         @RequestParam("czhIsbn") String czhIsbn,
                         @RequestParam("czhPublisher") String czhPublisher,
                         @RequestParam("czhPublishDate") String czhPublishDate,
                         @RequestParam("czhPrice") BigDecimal czhPrice,
                         @RequestParam("czhCategory") String czhCategory,
                         @RequestParam("czhDescription") String czhDescription,
                         @RequestParam("czhCoverImage") MultipartFile czhCoverImage,
                         HttpSession session,
                         Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }

        czhBook czhBook = new czhBook();
        czhBook.setCzhBookName(czhBookName);
        czhBook.setCzhAuthor(czhAuthor);
        czhBook.setCzhIsbn(czhIsbn);
        czhBook.setCzhPublisher(czhPublisher);
        // 安全处理日期解析
        if (czhPublishDate != null && !czhPublishDate.trim().isEmpty()) {
            try {
                czhBook.setCzhPublishDate(LocalDate.parse(czhPublishDate));
            } catch (Exception e) {
                // 如果日期解析失败，设置为当前日期
                czhBook.setCzhPublishDate(LocalDate.now());
            }
        } else {
            czhBook.setCzhPublishDate(LocalDate.now());
        }
        czhBook.setCzhPrice(czhPrice);
        czhBook.setCzhCategory(czhCategory);
        czhBook.setCzhDescription(czhDescription);

        // 处理文件上传
        if (!czhCoverImage.isEmpty()) {
            String czhImagePath = saveUploadedFile(czhCoverImage);
            czhBook.setCzhCoverImage(czhImagePath);
        }
        
        // 设置创建时间和更新时间
        czhBook.setCzhCreateTime(java.time.LocalDateTime.now());
        czhBook.setCzhUpdateTime(java.time.LocalDateTime.now());

        boolean czhSuccess = czhBookService.addBook(czhBook);
        if (czhSuccess) {
            return "redirect:/admin/home";
        } else {
            model.addAttribute("czhError", "添加图书失败，请检查输入信息是否正确");
            return "czh-book-add";
        }
    }

    /**
     * 显示编辑图书页面
     */
    @GetMapping("/book/edit/{czhBookId}")
    public String showEditBookPage(@PathVariable Integer czhBookId, HttpSession session, Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }

        czhBook czhBook = czhBookService.findBookById(czhBookId);
        model.addAttribute("czhBook", czhBook);
        return "czh-book-edit";
    }

    /**
     * 处理编辑图书请求
     */
    @PostMapping("/book/edit")
    public String editBook(@RequestParam("czhBookId") Integer czhBookId,
                          @RequestParam("czhBookName") String czhBookName,
                          @RequestParam("czhAuthor") String czhAuthor,
                          @RequestParam("czhIsbn") String czhIsbn,
                          @RequestParam("czhPublisher") String czhPublisher,
                          @RequestParam("czhPublishDate") String czhPublishDate,
                          @RequestParam("czhPrice") BigDecimal czhPrice,
                          @RequestParam("czhCategory") String czhCategory,
                          @RequestParam("czhDescription") String czhDescription,
                          @RequestParam(value = "czhCoverImage", required = false) MultipartFile czhCoverImage,
                          HttpSession session,
                          Model model) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }

        czhBook czhBook = new czhBook();
        czhBook.setCzhBookId(czhBookId);
        czhBook.setCzhBookName(czhBookName);
        czhBook.setCzhAuthor(czhAuthor);
        czhBook.setCzhIsbn(czhIsbn);
        czhBook.setCzhPublisher(czhPublisher);
        // 安全处理日期解析
        if (czhPublishDate != null && !czhPublishDate.trim().isEmpty()) {
            try {
                czhBook.setCzhPublishDate(LocalDate.parse(czhPublishDate));
            } catch (Exception e) {
                // 如果日期解析失败，设置为当前日期
                czhBook.setCzhPublishDate(LocalDate.now());
            }
        } else {
            czhBook.setCzhPublishDate(LocalDate.now());
        }
        czhBook.setCzhPrice(czhPrice);
        czhBook.setCzhCategory(czhCategory);
        czhBook.setCzhDescription(czhDescription);

        // 如果上传了新图片，更新图片路径
        if (czhCoverImage != null && !czhCoverImage.isEmpty()) {
            String czhImagePath = saveUploadedFile(czhCoverImage);
            czhBook.setCzhCoverImage(czhImagePath);
        }

        boolean czhSuccess = czhBookService.updateBook(czhBook);
        if (czhSuccess) {
            return "redirect:/admin/home";
        } else {
            model.addAttribute("czhError", "更新图书失败");
            return "czh-book-edit";
        }
    }

    /**
     * 删除图书
     */
    @GetMapping("/book/delete/{czhBookId}")
    public String deleteBook(@PathVariable Integer czhBookId, HttpSession session) {
        czhUser czhCurrentUser = (czhUser) session.getAttribute("czhCurrentUser");
        if (czhCurrentUser == null || !"czh_admin".equals(czhCurrentUser.getCzhRole())) {
            return "redirect:/user/login";
        }

        czhBookService.deleteBook(czhBookId);
        return "redirect:/admin/home";
    }

    /**
     * 保存上传的文件
     */
    private String saveUploadedFile(MultipartFile czhFile) {
        try {
            // 创建上传目录
            File czhUploadDir = new File(czhUploadPath);
            if (!czhUploadDir.exists()) {
                czhUploadDir.mkdirs();
            }

            // 生成唯一文件名
            String czhOriginalFilename = czhFile.getOriginalFilename();
            String czhExtension = czhOriginalFilename.substring(czhOriginalFilename.lastIndexOf("."));
            String czhNewFilename = UUID.randomUUID().toString() + czhExtension;

            // 保存文件
            File czhDestFile = new File(czhUploadDir, czhNewFilename);
            czhFile.transferTo(czhDestFile);

            return "/czh-upload/" + czhNewFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
