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
public class Discover implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    @TableField("picUrl")
    private String picurl;

    @TableField("farmId")
    private Integer farmid;

    @TableField(value="postTime",fill= FieldFill.INSERT)
    private LocalDateTime posttime;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
