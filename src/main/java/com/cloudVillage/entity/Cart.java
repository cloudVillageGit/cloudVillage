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
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("productId")
    private Integer productid;

    @TableField("userId")
    private Integer userid;

    @TableField("farmId")
    private Integer farmid;

    @TableLogic
    private Integer delLogic;


}
