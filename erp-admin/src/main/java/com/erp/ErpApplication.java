package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ERP 系统启动类
 */
@SpringBootApplication
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
        System.out.println("============================================");
        System.out.println("    ERP 系统启动成功！");
        System.out.println("    API 文档: http://localhost:8080/doc.html");
        System.out.println("============================================");
    }
}
