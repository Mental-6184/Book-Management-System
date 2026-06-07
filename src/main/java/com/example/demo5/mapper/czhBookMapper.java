package com.example.demo5.mapper;

import com.example.demo5.entity.czhBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 图书Mapper接口
 */
@Mapper
public interface czhBookMapper {
    
    /**
     * 查询所有图书
     */
    List<czhBook> findAllBooks();
    
    /**
     * 根据ID查询图书
     */
    czhBook findBookById(@Param("czhBookId") Integer czhBookId);
    
    /**
     * 插入图书
     */
    int insertBook(czhBook czhBook);
    
    /**
     * 更新图书
     */
    int updateBook(czhBook czhBook);
    
    /**
     * 删除图书
     */
    int deleteBook(@Param("czhBookId") Integer czhBookId);
    
    /**
     * 根据书名查询
     */
    List<czhBook> findByBookName(@Param("czhBookName") String czhBookName);
    
    /**
     * 根据作者查询
     */
    List<czhBook> findByAuthor(@Param("czhAuthor") String czhAuthor);
    
    /**
     * 根据ISBN查询
     */
    List<czhBook> findByIsbn(@Param("czhIsbn") String czhIsbn);
    
    /**
     * 多条件组合查询
     */
    List<czhBook> findByMultiConditions(@Param("czhBookName") String czhBookName,
                                        @Param("czhAuthor") String czhAuthor,
                                        @Param("czhIsbn") String czhIsbn,
                                        @Param("czhCategory") String czhCategory);
}
