package com.YYT.springbootbookmanager.service;

import com.YYT.springbootbookmanager.entity.LendList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少） 服务类
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
public interface ILendListService extends IService<LendList> {

    public List<LendList> queryLengListAll(LendList lendList);


    List<LendList> queryLookBookList(Integer rid, Integer bid);

    void updateLendListSubmit(List<String> ids, List<String> bookIds);
}
