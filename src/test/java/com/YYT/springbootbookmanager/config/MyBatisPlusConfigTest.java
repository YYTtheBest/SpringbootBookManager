package com.YYT.springbootbookmanager.config;

import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyBatisPlusConfigTest {
    @Resource
    private IBookInfoService bookInfoService;

    @Test
    public void testMybatisPlus_Page() {
        Page page = new Page<>(1, 5);
        Page page1 = bookInfoService.getBaseMapper().selectPage(page, null);
        page1.getRecords().forEach(e->{
            System.out.println(e);
        });
        System.out.println("当前页：" + page1.getCurrent());
        System.out.println("总页数：" + page1.getPages());
        System.out.println("记录数：" + page1.getTotal());
        System.out.println("是否有上一页：" + page1.hasPrevious());
        System.out.println("是否有下一页：" + page1.hasNext());

    }


}