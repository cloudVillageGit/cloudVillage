package com.cloudVillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 农产品分类表
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("categoryTop")
    private Integer categorytop;

    @TableField("categorySecond")
    private Integer categorysecond;

    @TableField("categoryThird")
    private Integer categorythird;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
