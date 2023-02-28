package com.YYT.springbootbookmanager.mapper;

import com.YYT.springbootbookmanager.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 图书信息 Mapper 接口
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    public List<BookInfo> queryBookInfoWithTypeInfo(BookInfo bookInfo);

    @Select("select count(book.id) as counts,ti.name from book_info book left join type_info ti on ti.tid = book.type_id group by book.type_id")
    public List<BookInfo> getBookCountByType();
}
