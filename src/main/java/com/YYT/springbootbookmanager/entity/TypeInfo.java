package com.YYT.springbootbookmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图书类型表
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("type_info")
public class TypeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书分类名称
     */
    @TableField("tname")
    private String name;

    /**
     * 备注
     */
    private String remarks;


}
