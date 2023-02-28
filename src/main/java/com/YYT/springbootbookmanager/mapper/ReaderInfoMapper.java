package com.YYT.springbootbookmanager.mapper;

import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 读者信息（包括登录账号密码等） Mapper 接口
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Mapper

public interface ReaderInfoMapper extends BaseMapper<ReaderInfo> {

}
