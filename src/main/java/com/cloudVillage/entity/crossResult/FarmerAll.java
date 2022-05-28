package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.Farmer;
import com.cloudVillage.entity.UserRealInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/28/15:10
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FarmerAll {
    private Farmer farmer;
    private Farm farm;
    private UserRealInfo userInfo;
}
