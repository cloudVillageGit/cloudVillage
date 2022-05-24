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
public class OrderTrackSmall implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 大标题
     */
    @TableField("headTitle")
    private String headtitle;

    /**
     * 小标题
     */
    @TableField("subTitle")
    private String subtitle;

    /**
     * 订单追踪中表（ordertracking_medium）ID
     */
    @TableField("ordtramedId")
    private Integer ordtramedid;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
