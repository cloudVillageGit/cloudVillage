package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.Evaluate;
import com.cloudVillage.entity.OrderInfo;
import com.cloudVillage.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/28/17:52
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderProductDetail {
    private OrderInfo orderInfo;
    private Product product;
    private Evaluate evaluate;
}
