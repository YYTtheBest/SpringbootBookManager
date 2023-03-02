package com.YYT.springbootbookmanager.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class pageHelper {
    /**
     *分页查询
     * @param current 首个条数
     * @param limit 每页限制
     * @param service IService服务
     * @param qwrap 默认为null，其他查询需求需要提供
     * @return
     */
    public Result defaultPage(int current, int limit, IService service, @DefaultValue(value = "null") QueryWrapper qwrap) {
        Page<Object> page = new Page<>(current, limit);
        IPage iPage = service.getBaseMapper().selectPage(page, qwrap);
        return Result.ok(iPage.getTotal(), iPage.getRecords());
    }
    /**
     *分页查询,返回查询列表做下一步处理
     * @param current 首个条数
     * @param limit 每页限制
     * @param service IService服务
     * @param qwrap 默认为null，其他查询需求需要提供
     * @return
     */
    public IPage PageIpage(int current, int limit, IService service, @DefaultValue(value = "null") QueryWrapper qwrap) {
        Page<Object> page = new Page<>(current, limit);
        IPage iPage = service.getBaseMapper().selectPage(page, qwrap);
        return iPage;
    }
}


