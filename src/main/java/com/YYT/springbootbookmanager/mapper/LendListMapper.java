package com.YYT.springbootbookmanager.mapper;

import com.YYT.springbootbookmanager.entity.LendList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少） Mapper 接口
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Mapper

public interface LendListMapper extends BaseMapper<LendList> {

    public List<LendList> queryLendListAll(LendList lendList);


    List<LendList> queryLookBookList(@Param("rid") Integer rid, @Param("bid") Integer bid);


    /**
     * 还书操作(正常还)
     */
    void updateLendListSubmit(LendList lendList);

}
