package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.OrderInfo;
import com.cloudVillage.entity.UserRealInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/28/16:06
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetail {
    private OrderInfo orderInfo;
    private Farm farm;
    private UserInfo userInfo;
    private Address address;
}
