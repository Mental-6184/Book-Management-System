
USE demo5;

-- 用户表
DROP TABLE IF EXISTS czh_user;
CREATE TABLE czh_user (
    czh_user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    czh_user_name VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    czh_password VARCHAR(255) NOT NULL COMMENT '密码（加密后）',
    czh_email VARCHAR(100) COMMENT '邮箱',
    czh_role VARCHAR(20) NOT NULL DEFAULT 'czh_user' COMMENT '角色：czh_admin 或 czh_user',
    czh_create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_name (czh_user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 图书表
DROP TABLE IF EXISTS czh_book;
CREATE TABLE czh_book (
    czh_book_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '图书ID',
    czh_book_name VARCHAR(200) NOT NULL COMMENT '书名',
    czh_author VARCHAR(100) COMMENT '作者',
    czh_isbn VARCHAR(50) COMMENT 'ISBN',
    czh_publisher VARCHAR(100) COMMENT '出版社',
    czh_publish_date DATE COMMENT '出版日期',
    czh_price DECIMAL(10, 2) COMMENT '价格',
    czh_category VARCHAR(50) COMMENT '分类',
    czh_description TEXT COMMENT '描述',
    czh_cover_image VARCHAR(255) COMMENT '封面图片路径',
    czh_create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    czh_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_book_name (czh_book_name),
    INDEX idx_author (czh_author),
    INDEX idx_isbn (czh_isbn)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

-- 插入默认管理员账户（密码：admin123）
INSERT INTO czh_user (czh_user_name, czh_password, czh_email, czh_role) 
VALUES ('admin', 'admin123', 'admin@example.com', 'czh_admin');

-- 插入测试用户（密码：user123）
INSERT INTO czh_user (czh_user_name, czh_password, czh_email, czh_role) 
VALUES ('user', 'user123', 'user@example.com', 'czh_user');

-- 插入测试图书数据
INSERT INTO czh_book (czh_book_name, czh_author, czh_isbn, czh_publisher, czh_publish_date, czh_price, czh_category, czh_description) 
VALUES 
('Java编程思想', 'Bruce Eckel', '9787111213826', '机械工业出版社', '2007-06-01', 108.00, '编程', 'Java经典著作'),
('Spring实战', 'Craig Walls', '9787115417305', '人民邮电出版社', '2016-04-01', 69.00, '框架', 'Spring框架实战指南'),
('数据库系统概念', 'Abraham Silberschatz', '9787111375296', '机械工业出版社', '2012-03-01', 89.00, '数据库', '数据库经典教材');
