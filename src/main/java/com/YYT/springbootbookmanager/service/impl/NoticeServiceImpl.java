package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.Notice;
import com.YYT.springbootbookmanager.mapper.NoticeMapper;
import com.YYT.springbootbookmanager.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
