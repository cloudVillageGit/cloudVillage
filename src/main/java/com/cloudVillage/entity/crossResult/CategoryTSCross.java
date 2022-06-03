package com.cloudVillage.entity.crossResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/06/03/10:08
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryTSCross {
    String categoryTop;
    Set<String> categorySecond;

}
