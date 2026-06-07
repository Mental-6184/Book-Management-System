package com.example.demo5.service;

import com.example.demo5.entity.czhBook;
import com.example.demo5.mapper.czhBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 图书服务类
 */
@Service
public class czhBookService {

    @Autowired
    private czhBookMapper czhBookMapper;

    /**
     * 查询所有图书
     */
    public List<czhBook> findAllBooks() {
        return czhBookMapper.findAllBooks();
    }

    /**
     * 根据ID查询图书
     */
    public czhBook findBookById(Integer czhBookId) {
        return czhBookMapper.findBookById(czhBookId);
    }

    /**
     * 添加图书
     */
    public boolean addBook(czhBook czhBook) {
        return czhBookMapper.insertBook(czhBook) > 0;
    }

    /**
     * 更新图书
     */
    public boolean updateBook(czhBook czhBook) {
        return czhBookMapper.updateBook(czhBook) > 0;
    }

    /**
     * 删除图书
     */
    public boolean deleteBook(Integer czhBookId) {
        return czhBookMapper.deleteBook(czhBookId) > 0;
    }

    /**
     * 根据书名查询
     */
    public List<czhBook> findByBookName(String czhBookName) {
        return czhBookMapper.findByBookName(czhBookName);
    }

    /**
     * 根据作者查询
     */
    public List<czhBook> findByAuthor(String czhAuthor) {
        return czhBookMapper.findByAuthor(czhAuthor);
    }

    /**
     * 根据ISBN查询
     */
    public List<czhBook> findByIsbn(String czhIsbn) {
        return czhBookMapper.findByIsbn(czhIsbn);
    }

    /**
     * 多条件组合查询
     */
    public List<czhBook> findByMultiConditions(String czhBookName, String czhAuthor, 
                                                String czhIsbn, String czhCategory) {
        return czhBookMapper.findByMultiConditions(czhBookName, czhAuthor, czhIsbn, czhCategory);
    }
}
