package com.YYT.springbootbookmanager.utils;

import com.YYT.springbootbookmanager.service.IBookInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class pageHelperTest {
    @Resource
    private pageHelper pageHelper;
    @Resource
    private IBookInfoService bookInfoService;
    @Test
    public void test(){
        Result result = pageHelper.defaultPage(2, 5, bookInfoService, null);
        System.out.println(result);

    }

}