package com.cloudVillage.entity;

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
public class OrderTrackMedium implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 大表（orderTracking）ID
     */
    @TableField("ordtraId")
    private Integer ordtraid;

    @TableField(value = "workTime",fill=FieldFill.INSERT)
    private LocalDateTime worktime;

    /**
     * 图片
     */
    @TableField("workImg")
    private String workimg;

    /**
     * 逻辑删除位置 0未删除 1已经删除
     */
    @TableLogic
    private Integer delLogic;


}
