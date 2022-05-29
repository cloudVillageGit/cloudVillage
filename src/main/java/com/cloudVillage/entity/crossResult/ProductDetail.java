package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 用于具体页面的展示
 * @Author: Link
 * @Date: 2022/05/27/16:17
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDetail {
    private Product product;
    private Category category;
    private Farm farm;
    private List<String> mainUrlList;
    private List<String> otherUrlList;
    private List<ProductSmall> productSmallList;
}
