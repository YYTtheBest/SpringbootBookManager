package com.example.homework1.mapper;

import com.example.homework1.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
* @author 14361
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2023-02-27 19:45:41
* @Entity com.example.homework1.entity.Teacher
*/
@Mapper
public interface TeacherMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    @Select("select * from teacher")
    List<Teacher> selectAll();

}
