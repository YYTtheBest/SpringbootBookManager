package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.mapper.BookInfoMapper;
import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.List;

/**
 * <p>
 * 图书信息 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements IBookInfoService {

    @Resource
    private BookInfoMapper bookInfoMapper;


    @Override
    public List<BookInfo> queryBookInfoWithTypeInfo(BookInfo bookInfo) {

        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoWithTypeInfo(bookInfo);
        return bookInfos;

    }

    @Override
    public List<BookInfo> listAllBookInfos(BookInfo bookInfo,int pageNum, int limit) {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();

        if (bookInfo.getName()!=null)
        bookInfoQueryWrapper.like("name", bookInfo.getName());

        if (bookInfo.getIsbn() != null)
            bookInfoQueryWrapper.like("isbn", bookInfo.getIsbn());
        if (bookInfo.getTypeId() != null)
            bookInfoQueryWrapper.like("typeid", bookInfo.getTypeId());

        List<BookInfo> bookInfos = bookInfoMapper.selectList(bookInfoQueryWrapper);


        return bookInfos;
    }





    @Override
    public List<BookInfo> getBookCountByTypes() {
        List<BookInfo> bookCountByType = bookInfoMapper.getBookCountByType();
        return bookCountByType;
    }


}
