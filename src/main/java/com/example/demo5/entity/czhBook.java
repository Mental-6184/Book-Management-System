package com.example.demo5.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 图书实体类
 */
public class czhBook {
    private Integer czhBookId;
    private String czhBookName;
    private String czhAuthor;
    private String czhIsbn;
    private String czhPublisher;
    private LocalDate czhPublishDate;
    private BigDecimal czhPrice;
    private String czhCategory;
    private String czhDescription;
    private String czhCoverImage;
    private LocalDateTime czhCreateTime;
    private LocalDateTime czhUpdateTime;

    // 构造方法
    public czhBook() {
    }

    // Getter 和 Setter 方法
    public Integer getCzhBookId() {
        return czhBookId;
    }

    public void setCzhBookId(Integer czhBookId) {
        this.czhBookId = czhBookId;
    }

    public String getCzhBookName() {
        return czhBookName;
    }

    public void setCzhBookName(String czhBookName) {
        this.czhBookName = czhBookName;
    }

    public String getCzhAuthor() {
        return czhAuthor;
    }

    public void setCzhAuthor(String czhAuthor) {
        this.czhAuthor = czhAuthor;
    }

    public String getCzhIsbn() {
        return czhIsbn;
    }

    public void setCzhIsbn(String czhIsbn) {
        this.czhIsbn = czhIsbn;
    }

    public String getCzhPublisher() {
        return czhPublisher;
    }

    public void setCzhPublisher(String czhPublisher) {
        this.czhPublisher = czhPublisher;
    }

    public LocalDate getCzhPublishDate() {
        return czhPublishDate;
    }

    public void setCzhPublishDate(LocalDate czhPublishDate) {
        this.czhPublishDate = czhPublishDate;
    }

    public BigDecimal getCzhPrice() {
        return czhPrice;
    }

    public void setCzhPrice(BigDecimal czhPrice) {
        this.czhPrice = czhPrice;
    }

    public String getCzhCategory() {
        return czhCategory;
    }

    public void setCzhCategory(String czhCategory) {
        this.czhCategory = czhCategory;
    }

    public String getCzhDescription() {
        return czhDescription;
    }

    public void setCzhDescription(String czhDescription) {
        this.czhDescription = czhDescription;
    }

    public String getCzhCoverImage() {
        return czhCoverImage;
    }

    public void setCzhCoverImage(String czhCoverImage) {
        this.czhCoverImage = czhCoverImage;
    }

    public LocalDateTime getCzhCreateTime() {
        return czhCreateTime;
    }

    public void setCzhCreateTime(LocalDateTime czhCreateTime) {
        this.czhCreateTime = czhCreateTime;
    }

    public LocalDateTime getCzhUpdateTime() {
        return czhUpdateTime;
    }

    public void setCzhUpdateTime(LocalDateTime czhUpdateTime) {
        this.czhUpdateTime = czhUpdateTime;
    }

    @Override
    public String toString() {
        return "czhBook{" +
                "czhBookId=" + czhBookId +
                ", czhBookName='" + czhBookName + '\'' +
                ", czhAuthor='" + czhAuthor + '\'' +
                ", czhIsbn='" + czhIsbn + '\'' +
                ", czhPublisher='" + czhPublisher + '\'' +
                ", czhPublishDate=" + czhPublishDate +
                ", czhPrice=" + czhPrice +
                ", czhCategory='" + czhCategory + '\'' +
                ", czhCoverImage='" + czhCoverImage + '\'' +
                '}';
    }
}
