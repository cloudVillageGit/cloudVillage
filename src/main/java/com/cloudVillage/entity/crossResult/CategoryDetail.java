package com.cloudVillage.entity.crossResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/27/21:41
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryDetail {

    private String categorySecond;
    private List<Map<String,String>>  categoryThird;
}
