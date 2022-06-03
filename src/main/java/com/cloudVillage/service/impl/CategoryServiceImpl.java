package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Category;
import com.cloudVillage.entity.Picture;
import com.cloudVillage.entity.crossResult.CategoryDetail;
import com.cloudVillage.entity.crossResult.CategoryTSCross;
import com.cloudVillage.mapper.CategoryMapper;
import com.cloudVillage.mapper.PictureMapper;
import com.cloudVillage.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 农产品分类表 服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private PictureMapper pictureMapper;


    /**
     * 目录使用
     * @param keyWord
     * @return
     */
    @Override
    public ResponseResult CategoryList(String keyWord) {

        // 待返回的类型
        List<CategoryDetail> list = new ArrayList<>();


        // Set 二级分类
        HashSet<String> secondSet = new HashSet<>();



        // 查询对应点击事件
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("categoryTop",keyWord);
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);

        // 若查询失败
        if(categories.size()==0){
            return new ResponseResult(500,"categoryTop查询失败");
        }

        // 循环进行分类
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            // 获取二级分类
            String categorysecond = category.getCategorysecond();
            // 判断是否出现过
            // 如果出现过
            if(secondSet.contains(categorysecond)){
                continue;
            }else{
                // 如果未出现过
                secondSet.add(category.getCategorysecond());
            }
        }

        // 获取已有种类数量
        // Set 转为 List 二级分类
        List<String> secondList = new ArrayList<>(secondSet);

        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();


        for (int i = 0; i < secondList.size(); i++) {

            CategoryDetail categoryDetail = new CategoryDetail();
            // 获取二级名
            categoryDetail.setCategorySecond(secondList.get(i));
            // 创建三级分类名称列表
            List<Map<String,String>> thirdList = new ArrayList<>();

            for (int j = 0; j < categories.size(); j++) {
                // 图片，url
                Map<String,String> map = new HashMap<>();

                Category category = categories.get(j);
                Integer categoryId = category.getId();
                String categorythird = category.getCategorythird();
                String categorysecond = category.getCategorysecond();


                // 若当前category与当前判断分类相等
                if(categorysecond.equals(secondList.get(i))){

                    pictureQueryWrapper.eq("id",categoryId);
                    pictureQueryWrapper.eq("chartsName","category");
                    List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);

                    // 数据库里面没有
                    if(pictures.size()==0){
                        map.put("image",null);
                    }else {
                        Picture picture = pictures.get(0);
                        map.put("image", picture.getPicurl());
                    }
                    map.put("name",categorythird);
                    thirdList.add(map);

                }

            }
            // 赋值
            categoryDetail.setCategoryThird(thirdList);

            list.add(categoryDetail);
        }
        System.out.println(list.toString());

        return new ResponseResult(list);
    }

    @Override
    public ResponseResult searchTop(String categoryTop) {
        List<CategoryTSCross> categoryTSCrossList = new ArrayList<>();
        QueryWrapper<Category> categoryQueryWrapper= new QueryWrapper<>();
        if(categoryTop!=null) {
            categoryQueryWrapper.eq("categoryTop", categoryTop);
        }
        List<Category> categoryList = categoryMapper.selectList(categoryQueryWrapper);
        if(categoryList.size()==0){
            return new ResponseResult(500,"查询失败");
        }else{
            Set<String> categoryTopNameSet = new HashSet<>();
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                categoryTopNameSet.add(category.getCategorytop());
            }
            for (String s : categoryTopNameSet) {
                CategoryTSCross categoryTSCross = new CategoryTSCross();
                categoryQueryWrapper.clear();
                categoryTSCross.setCategoryTop(s);

                categoryQueryWrapper.eq("categoryTop",s);
                List<Category> _categoryList = categoryMapper.selectList(categoryQueryWrapper);
                Set<String> _categorySecondNameList = new HashSet<>();
                for (int i = 0; i < _categoryList.size(); i++) {
                    Category category = _categoryList.get(i);
                    String categorysecond = category.getCategorysecond();
                    _categorySecondNameList.add(categorysecond);
                }
                categoryTSCross.setCategorySecond(_categorySecondNameList);
                categoryTSCrossList.add(categoryTSCross);
            }
        }
        return new ResponseResult(200,"查询成功",categoryTSCrossList);
    }

    @Override
    public ResponseResult searchSecond(String categorySecond) {
        QueryWrapper<Category> categoryQueryWrapper= new QueryWrapper<>();
        categoryQueryWrapper.eq("categorySecond",categorySecond);
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
        Set<String> thirdNameSet = new HashSet<>();
        if(categories.size() == 0){
            return new ResponseResult(500,"查询三级目录失败");
        }else{
            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                thirdNameSet.add(category.getCategorythird());
            }
            return new ResponseResult(200,"查询三级目录成功",thirdNameSet);
        }
    }
}
