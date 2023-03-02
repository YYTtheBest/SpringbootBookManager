package com.YYT.springbootbookmanager;

import lombok.extern.java.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans({
        @MapperScan("com.YYT.springbootbookmanager.mapper")
})
@Log
public class SpringbootBookManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookManagerApplication.class, args);
        log.info("\n" +
                "｀　　　　　　　　　　　　　　　　　　　　ｔ　　　　　　　　　　８８８　　　　０００　　　　９９９　　　　０００\n" +
                "　　　　　　　　　　　　　　　　　　　　　ｔ　　　　　　　　　８　　　８　　０　　　０　　９　　　９　　０　　　０\n" +
                "ｐｐｐｐ　　　　ｏｏｏ　　　ｒ　ｒｒ　　ｔｔｔｔ　　　　　　　８　　　８　　０　　　０　　９　　　９　　０　　　０\n" +
                "ｐ　　　ｐ　　ｏ　　　ｏ　　ｒｒ　　　　　ｔ　　　　　：　　　８　　　８　　０　　　０　　９　　　９　　０　　　０\n" +
                "ｐ　　　ｐ　　ｏ　　　ｏ　　ｒ　　　　　　ｔ　　　　　：　　　　８８８　　　０　　　０　　９　　　９　　０　　　０\n" +
                "ｐ　　　ｐ　　ｏ　　　ｏ　　ｒ　　　　　　ｔ　　　　　　　　　８　　　８　　０　　　０　　　９９９９　　０　　　０\n" +
                "ｐ　　　ｐ　　ｏ　　　ｏ　　ｒ　　　　　　ｔ　　　　　　　　　８　　　８　　０　　　０　　　　　　９　　０　　　０\n" +
                "ｐｐｐｐ　　　ｏ　　　ｏ　　ｒ　　　　　　ｔ　　　　　：　　　８　　　８　　０　　　０　　９　　　９　　０　　　０\n" +
                "ｐ　　　　　　　ｏｏｏ　　　ｒ　　　　　　　ｔｔ　　　：　　　　８８８　　　　０００　　　　９９９　　　　０００\n" +
                "ｐ");

    }

}
