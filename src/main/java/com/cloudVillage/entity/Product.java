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
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("productName")
    private String productname;

    /**
     * 0云产品 1现货产品
     */
    private Boolean stype;

    /**
     * 现价格
     */
    private BigDecimal priceNow;

    /**
     * 前价格
     */
    private BigDecimal pricePre;

    /**
     * 库存数量
     */
    private Integer storage;

    /**
     * 已经销售数量
     */
    private Integer selled;

    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 产地
     */
    @TableField("grownArea")
    private String grownarea;

    /**
     * 分类id
     */
    @TableField("classId")
    private Integer classid;

    /**
     * 农场id
     */
    @TableField("farmId")
    private Integer farmid;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
