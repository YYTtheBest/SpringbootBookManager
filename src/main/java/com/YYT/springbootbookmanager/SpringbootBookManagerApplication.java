package com.YYT.springbootbookmanager;

import lombok.extern.java.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Indexed;

@SpringBootApplication
@MapperScans({
        @MapperScan("com.YYT.springbootbookmanager.mapper")
})
@Log
@Indexed
public class SpringbootBookManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookManagerApplication.class, args);
        
    }

}
