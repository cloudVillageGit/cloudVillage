package com.cloudVillage.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("openId")
    private Integer openid;

    /**
     * 创建时间
     */
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private LocalDateTime createtime;

    /**
     * 头像url
     */
    @TableField("avatarUrl")
    private String avatarurl;

    /**
     * 用户名
     */
    @TableField("nickName")
    private String nickname;

    /**
     * 田币
     */
    private BigDecimal coin;

    /**
     * 逻辑删除位 0未删除 1已删除
     */
    @TableLogic
    private Integer delLogic;


}
