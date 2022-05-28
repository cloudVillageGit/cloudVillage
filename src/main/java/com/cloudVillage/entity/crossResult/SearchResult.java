package com.cloudVillage.entity.crossResult;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.cloudVillage.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created with IntelliJ IDEA.
 *
 *  初步查询结果类
 *
 * @Author: Link
 * @Date: 2022/05/27/15:42
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchResult implements Serializable {
    private Product product;
    private String picUrl;
}
