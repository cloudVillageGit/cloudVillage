package com.cloudVillage.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
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
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userid;

    @TableField("farmId")
    private Integer farmid;

    @TableField("addressId")
    private Integer addressid;

    @TableField("orderStatus")
    private Integer orderstatus;

    private BigDecimal postage;

    @TableField(value = "createTime",fill= FieldFill.INSERT)
    private LocalDateTime createtime;

    @TableField("payTime")
    private LocalDateTime paytime;

    @TableField("finishTime")
    private LocalDateTime finishtime;

    @TableField("actualPay")
    private BigDecimal actualpay;

    @TableField("coinDecade")
    private BigDecimal coindecade;

    /**
     * 订单编号
     */
    @TableField("orderNum")
    private String ordernum;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
