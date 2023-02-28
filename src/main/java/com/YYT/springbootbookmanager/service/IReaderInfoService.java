package com.YYT.springbootbookmanager.service;

import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 读者信息（包括登录账号密码等） 服务类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
public interface IReaderInfoService extends IService<ReaderInfo> {
    public List<ReaderInfo> listAllReaderInfos(ReaderInfo readerInfo) ;


    }
