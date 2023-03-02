package com.YYT.springbootbookmanager.service;

import com.YYT.springbootbookmanager.entity.BookInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.print.Book;
import java.util.List;

/**
 * <p>
 * 图书信息 服务类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
public interface IBookInfoService extends IService<BookInfo> {

    public List<BookInfo> queryBookInfoWithTypeInfo(BookInfo bookInfo);


    public List<BookInfo> listAllBookInfos(BookInfo bookInfo,int pageNum, int limit) ;


    public List<BookInfo> getBookCountByTypes();

    }
