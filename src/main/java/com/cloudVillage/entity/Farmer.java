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
 * 
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Farmer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 农场Id
     */
    @TableField("farmId")
    private Integer farmid;

    /**
     * 农户简介
     */
    private String profiles;

    /**
     * user_real_info表外键
     */
    @TableField("user_real_info_Id")
    private Integer userRealInfoId;

    /**
     * 逻辑删除 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
