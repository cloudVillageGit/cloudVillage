package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.Address;
import com.cloudVillage.entity.Farm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/31/15:42
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FarmAddress {
    private Farm farm;
    private Address address;
}
