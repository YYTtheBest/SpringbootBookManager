package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.TypeInfo;
import com.YYT.springbootbookmanager.mapper.TypeInfoMapper;
import com.YYT.springbootbookmanager.service.ITypeInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>
 * 图书类型表 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class TypeInfoServiceImpl extends ServiceImpl<TypeInfoMapper, TypeInfo> implements ITypeInfoService {
    @Resource
    private TypeInfoMapper typeInfoMapper;

    @Override
    public List<TypeInfo> queryAllTypeInfos(String name, int pageNum, int limit) {

        QueryWrapper<TypeInfo> typeInfoQueryWrapper = new QueryWrapper<>();
        if (name != null) {
            typeInfoQueryWrapper.like("name", name);
        }
        return typeInfoMapper.selectList(typeInfoQueryWrapper);
    }
}
