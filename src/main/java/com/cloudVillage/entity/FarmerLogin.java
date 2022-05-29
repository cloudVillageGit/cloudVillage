package com.cloudVillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

}
