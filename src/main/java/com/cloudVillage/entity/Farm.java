package com.cloudVillage.entity;

import java.math.BigDecimal;
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
public class Farm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("farmName")
    private String farmname;

    @TableField("addressId")
    private Integer addressid;

    private String profiles;

    /**
     * 已售金额
     */
    @TableField("selledAmount")
    private BigDecimal selledamount;

    private Integer rank;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
