package com.cloudVillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/14:13
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FarmerLogin {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 农场id
     */
    @TableField("fName")
    private String fname;

    @TableField("fPassword")
    private String fpassword;

    @TableField("farmId")
    private Integer farmid;

    /**
     * 逻辑删除 0未删除 1已删除
     */
    @TableLogic
    private String delLogic;
}
