package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.YYT.springbootbookmanager.mapper.ReaderInfoMapper;
import com.YYT.springbootbookmanager.service.IReaderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 读者信息（包括登录账号密码等） 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class ReaderInfoServiceImpl extends ServiceImpl<ReaderInfoMapper, ReaderInfo> implements IReaderInfoService {

    @Resource
    private IReaderInfoService readerInfoService;

    @Override
    public List<ReaderInfo> listAllReaderInfos(ReaderInfo readerInfo) {
        QueryWrapper<ReaderInfo> readerInfoQueryWrapper = new QueryWrapper<>();
        if (readerInfo.getReaderNumber()!=null)
            readerInfoQueryWrapper.like("readerNumber", readerInfo.getReaderNumber());

        if (readerInfo.getUsername() != null)
            readerInfoQueryWrapper.like("username", readerInfo.getUsername());
        if (readerInfo.getTel() != null)
            readerInfoQueryWrapper.like("tel", readerInfo.getTel());

        List<ReaderInfo> list = readerInfoService.list(readerInfoQueryWrapper);
        return list;

    }
}
