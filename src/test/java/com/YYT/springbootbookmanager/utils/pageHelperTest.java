package com.YYT.springbootbookmanager.utils;

import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class pageHelperTest {
    @Resource
    private pageHelper pageHelper;
    @Resource
    private IBookInfoService bookInfoService;

    @Test
    public void test() {
        IPage iPage = pageHelper.PageIpage(1, 30, bookInfoService, null);
        System.out.println(iPage.getPages());

    }

}