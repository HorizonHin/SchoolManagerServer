package com.schoolmanagerserver;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.schoolmanagerserver.mapper")
public class SchoolManagerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolManagerServerApplication.class, args);
    }

}
