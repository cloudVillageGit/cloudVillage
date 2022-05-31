package com.cloudVillage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.*;
import com.cloudVillage.entity.crossResult.ProductDetail;
import com.cloudVillage.entity.crossResult.SearchResult;
import com.cloudVillage.mapper.*;
import com.cloudVillage.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private ProductSmallMapper productSmallMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private FarmMapper farmMapper;

    @Override
    public ResponseResult searchProduct(String keyWord) {
        // 根据输入关键字进行模糊查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("productName",keyWord);
        List<Product> productResults = productMapper.selectList(queryWrapper);
        List<SearchResult> searchResultList = new ArrayList<>();

        if(productResults.size()==0){
            return new ResponseResult(500,"查询失败");
        }
        for(int i = 0; i < productResults.size(); i++) {
            // 查询结果相对应的图片
            QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
            // 具体到每一个农产品进行对应图片的查询
            Product product = productResults.get(i);

            SearchResult searchResult = new SearchResult();
            searchResult.setProduct(product);

            Integer id = product.getId();
            pictureQueryWrapper.eq("chartsName","product");
            pictureQueryWrapper.eq("judgeStype","0");
            pictureQueryWrapper.eq("charsId",id);
            List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);

            if(pictures.size()==0){
                searchResult.setPicUrl(null);
//                return new ResponseResult(500,"农产品图片查询失败");
            }else{

                // 主图仅有一张
                Picture picture = pictures.get(0);
                String picurl = picture.getPicurl();
                searchResult.setPicUrl(picurl);
            }
            searchResultList.add(searchResult);
        }

        return new ResponseResult(searchResultList);
    }

    @Override
    public ResponseResult productDetail(Integer id) {
        ProductDetail productDetail = new ProductDetail();

        // 通过id查询农产品表
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        List<Product> products = productMapper.selectList(queryWrapper);
        // 未查询到数据
        if(products.size()==0){
            return new ResponseResult(500,"农产品查询失败");
        }

        // 只有一个实体对象
        Product product = products.get(0);

        // 给product
        productDetail.setProduct(product);

        // 查询对应分类
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("id",product.getClassid());
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
        // 未查询到数据
        if(categories.size()==0){
            return new ResponseResult(500,"分类查询失败");
        }
        // 分类 赋值
        Category category = categories.get(0);
        productDetail.setCategory(category);

        // 农场查询
        QueryWrapper<Farm> farmQueryWrapper = new QueryWrapper<>();
        farmQueryWrapper.eq("id",product.getFarmid());
        List<Farm> farms = farmMapper.selectList(farmQueryWrapper);
        // 未查询到数据
        if(farms.size()==0){
            return new ResponseResult(500,"农场查询失败");
        }
        // 农场 赋值
        Farm farm = farms.get(0);
        productDetail.setFarm(farm);

        // 农产品*属性*查询 （productSmall)
        QueryWrapper<ProductSmall> productSmallWrapper = new QueryWrapper<>();
        productSmallWrapper.eq("productId",id);
        List<ProductSmall> productSmallList = productSmallMapper.selectList(productSmallWrapper);
        // 未查询到数据
        if(productSmallList.size()==0){
//            return new ResponseResult(500,"农场查询失败");
            productDetail.setProductSmallList(null);
        }else {
            // 农产品*属性*赋值
            productDetail.setProductSmallList(productSmallList);
        }
        // 农产品图片
        // 查询结果相对应的图片
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        // 具体到每一个农产品进行对应图片的查询

        // 前五张图片
        pictureQueryWrapper.eq("chartsName","product");
        pictureQueryWrapper.between("judgeStype","1","5");
        pictureQueryWrapper.eq("charsId",id);

        List<Picture> mainPictures = pictureMapper.selectList(pictureQueryWrapper);
        List<String> mainPicturesUrl = new ArrayList<>();
        // 未查询到数据
        if(mainPictures.size()==0){
//            return new ResponseResult(500,"主图片查询失败");
            productDetail.setMainUrlList(null);
        }else {
            for (int i = 0; i < mainPictures.size(); i++) {
                Picture picture = mainPictures.get(i);
                String picurl = picture.getPicurl();
                mainPicturesUrl.add(picurl);
            }
            // 主图url赋值
            productDetail.setMainUrlList(mainPicturesUrl);
        }
        // 清空查询条件
        pictureQueryWrapper.clear();
        // 查询其余图片
        pictureQueryWrapper.eq("chartsName","product");
        pictureQueryWrapper.gt("judgeStype","5");
        pictureQueryWrapper.eq("charsId",id);

        List<Picture> otherPictures = pictureMapper.selectList(pictureQueryWrapper);
        List<String> otherPicturesUrl = new ArrayList<>();
        if(otherPictures.size()==0){
//            return new ResponseResult(500,"副图片查询失败");
            productDetail.setOtherUrlList(null);
        }else {
            for (int i = 0; i < otherPictures.size(); i++) {
                Picture picture = otherPictures.get(i);
                String picurl = picture.getPicurl();

                otherPicturesUrl.add(picurl);
            }
            // 其余图片url赋值
            productDetail.setOtherUrlList(otherPicturesUrl);
        }
        return new ResponseResult(productDetail);
    }

    @Override
    public int updateProduct(Product product) {
        UpdateWrapper<Product> productUpdateWrapper = new UpdateWrapper<>();
        productUpdateWrapper.eq("id",product.getId());
        int update = productMapper.update(product, productUpdateWrapper);
        return update;
    }

    @Override
    public int deleteProduct(Integer id) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("id",id);
        List<Product> products = productMapper.selectList(productQueryWrapper);
        if(products.size() == 0){
            return -1;
        }
        int delete = productMapper.delete(productQueryWrapper);
        return delete;
    }

    @Override
    public int insertProduct(Product product) {
        int insert = productMapper.insert(product);
        return insert;
    }

    @Override
    public ResponseResult selectByFarmFBE(Integer farmId,String keyWord) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("farmId",farmId);

        List<ProductDetail> productDetails = new ArrayList<>();

        if(keyWord != null){
            productQueryWrapper.like("productName",keyWord);
        }

        List<Product> products = productMapper.selectList(productQueryWrapper);

        if(products.size() == 0){
            return new ResponseResult(500,"查询错误,无数据");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Integer id = product.getId();
            ResponseResult responseResult = this.productDetail(id);
            ProductDetail productDetail = (ProductDetail) responseResult.getData();
            productDetails.add(productDetail);
        }

        return new ResponseResult(productDetails);
    }


}
