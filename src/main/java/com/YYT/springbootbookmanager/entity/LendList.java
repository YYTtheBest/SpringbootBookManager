package com.YYT.springbootbookmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * 借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lend_list")
public class LendList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 读者id
     */
    private Integer readerId;

    /**
     * 借书时间
     */
    private LocalDateTime lendDate;

    /**
     * 还书时间
     */
    private LocalDateTime backDate;

    private Integer backType;

    /**
     * 备注信息
     */
    private String exceptRemarks;


    @TableField(exist = false)
    private BookInfo bookInfo;

    @TableField(exist = false)
    private ReaderInfo readerInfo;


}
