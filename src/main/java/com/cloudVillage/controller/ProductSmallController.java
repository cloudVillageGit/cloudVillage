package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.ProductSmall;
import com.cloudVillage.entity.User;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.service.IProductSmallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/productSmall")
public class ProductSmallController {
    @Autowired
    private IProductSmallService productSmallService;
    /**
     * 修改商品信息
     */
    @PutMapping("updateProductSmall")
    public ResponseResult updateUserInfo(@RequestBody ProductSmall productSmall) {
        Integer update = productSmallService.updateProductSmall(productSmall);
        if(update==0){
            return new ResponseResult(500,"更新商品信息失败");
        }else{
            return new ResponseResult(200,"更新商品信息成功");
        }
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteProductSmall")
    public ResponseResult deleteUser(@RequestParam Integer id){
        int delete = productSmallService.deleteProductSmall(id);
        if(delete==1){
            return new ResponseResult(200,"删除商品信息成功");
        }else if(delete==0){
            return new ResponseResult(500,"删除商品信息失败");
        }else {
            return new ResponseResult(500,"商品信息已逻辑删除");
        }
    }

    /**
     * 插入商品信息
     * @param productSmall
     * @return
     */
    @PostMapping("insertProductSmall")
    public ResponseResult insertUserRealInfo(@RequestBody ProductSmall productSmall){
        int insertProductSmall = productSmallService.insertProductSmall(productSmall);
        if(insertProductSmall == 1){
            return new ResponseResult(200,"插入商品信息成功");
        }else{
            return new ResponseResult(500,"插入商品信息失败");
        }
    }

    /**
     * 通过id获取商品信息
     * @param id
     * @return
     */
    @PostMapping("selectProductSmall")
    public ResponseResult selectProductSmall(@RequestParam Integer id){
        ResponseResult selectProductSmall = productSmallService.selectProductSmall(id);
        if(selectProductSmall.getCode()==null){
            return new ResponseResult(200,"查询商品信息成功",selectProductSmall);
        }else{
            return new ResponseResult(500,"查询商品信息失败");
        }
    }
}
