package com.YYT.springbootbookmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans({
        @MapperScan("com.YYT.springbootbookmanager.mapper")
})
public class SpringbootBookManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookManagerApplication.class, args);
    }

}
