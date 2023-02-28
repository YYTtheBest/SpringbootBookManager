package com.YYT.springbootbookmanager.service;

import com.YYT.springbootbookmanager.entity.TypeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>
 * 图书类型表 服务类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
public interface ITypeInfoService extends IService<TypeInfo> {
    public List<TypeInfo> queryAllTypeInfos(String name, int pageNum, int limit);
}
