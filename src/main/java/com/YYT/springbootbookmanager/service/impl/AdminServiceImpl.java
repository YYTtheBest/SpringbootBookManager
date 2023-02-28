package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.Admin;
import com.YYT.springbootbookmanager.mapper.AdminMapper;
import com.YYT.springbootbookmanager.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
