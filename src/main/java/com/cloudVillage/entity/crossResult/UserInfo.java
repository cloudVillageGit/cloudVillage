package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.User;
import com.cloudVillage.entity.UserRealInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *  获取用户的所有信息
 * @Author: Link
 * @Date: 2022/05/28/11:33
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {
    private User user;
    private UserRealInfo userRealInfo;
}
