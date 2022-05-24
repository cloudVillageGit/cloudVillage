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
 * 用户真实信息
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRealInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户真实姓名
     */
    @TableField("realName")
    private String realname;

    /**
     * 用户身份证
     */
    @TableField("idcardNum")
    private String idcardnum;

    /**
     * 逻辑删除位置 0未删除 1已经删除
     */
    @TableLogic
    private Integer delLogic;


}
