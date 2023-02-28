package com.YYT.springbootbookmanager.mapper;

import com.YYT.springbootbookmanager.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}