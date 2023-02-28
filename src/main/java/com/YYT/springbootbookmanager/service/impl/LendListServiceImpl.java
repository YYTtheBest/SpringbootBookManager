package com.YYT.springbootbookmanager.service.impl;

import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.entity.LendList;
import com.YYT.springbootbookmanager.mapper.LendListMapper;
import com.YYT.springbootbookmanager.service.ILendListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少） 服务实现类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Service
public class LendListServiceImpl extends ServiceImpl<LendListMapper, LendList> implements ILendListService {

    @Resource
    private LendListMapper lendListMapper;

    @Resource
    BookInfoServiceImpl bookInfoService;
    @Resource
    ReaderInfoServiceImpl readerInfoService;
    @Override
    public List<LendList> queryLengListAll(LendList lendList) {
        List<LendList> lendLists = lendListMapper.queryLendListAll(lendList);
        for (int i = 0; i < lendLists.size(); i++) {
            LendList lendList1 = lendLists.get(i);
            lendList1.setBookInfo(bookInfoService.getById(lendList1.getBookId()));
            lendList1.setReaderInfo(readerInfoService.getById(lendList1.getReaderId()));
        }
        return lendLists;

    }

    @Override
    public List<LendList> queryLookBookList(Integer rid, Integer bid) {
        List<LendList> lendLists = lendListMapper.queryLookBookList(rid, bid);
        lendLists.forEach(e->{
            e.setBookInfo(bookInfoService.getById(e.getBookId()));
            e.setReaderInfo(readerInfoService.getById(e.getReaderId()));
        });
        return lendLists;
    }

    @Override
    public void updateLendListSubmit(List<String> ids, List<String> bookIds) {
        for(String id:ids){
            //根据id查询借阅记录信息
            LendList lendList=new LendList();
            lendList.setId(Integer.parseInt(id));
            lendList.setBackDate(LocalDateTime.now());
            lendList.setBackType(0);//正常还书
            lendListMapper.updateLendListSubmit(lendList);
        }
        //修改书的状态
        //更改图书标识，更新状态为未借出
        for(String bid:bookIds){
            //根据id查询图书记录信息
            BookInfo bookInfo=bookInfoService.getById(Integer.parseInt(bid));
            bookInfo.setStatus(0);//该为未借出
            bookInfoService.updateById(bookInfo);
        }
    }

}
