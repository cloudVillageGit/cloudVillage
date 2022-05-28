package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.Category;
import com.cloudVillage.entity.Farm;
import com.cloudVillage.entity.Picture;
import com.cloudVillage.entity.ProductSmall;
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
    private ProductSmall productSmall;
    private Category category;
    private Farm farm;
    private List<String> mainUrlList;
    private List<String> otherUrlList;
    private List<ProductSmall> productSmallList;
}
