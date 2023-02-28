package com.YYT.springbootbookmanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * <p>
 * 图书信息
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("book_info")
@ToString
public class BookInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publish;

    /**
     * 书籍编号
     */

    private String isbn;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 语言
     */
    private String language;

    /**
     * 价格
     */
    private Double price;

    /**
     * 出版时间
     */
    @TableField("publish_date")
    private LocalDate publishDate;

    /**
     * 书籍类型
     */

    @TableField("type_id")
    private Integer typeId;

    /**
     * 状态：0未借出，1已借出
     */
    private Integer status;

    @TableField(exist = false)
    private Integer counts;

@TableField(exist = false)

    private TypeInfo typeInfo;

}
