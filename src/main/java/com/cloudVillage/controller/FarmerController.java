package com.cloudVillage.controller;



import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Farmer;
import com.cloudVillage.service.IFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/farmer")
public class FarmerController {
    @Qualifier
    private IFarmerService farmerService;
    /**
     * 修改个人信息
     */
    @PutMapping("updateFarmer")
    public ResponseResult updateFarmer(@RequestBody Farmer farmer) {
        Integer update = farmerService.updateFarmer(farmer);
        if(update==0){
            return new ResponseResult(500,"更新农户信息失败");
        }else{
            return new ResponseResult(200,"更新农户信息成功");
        }
    }

    /**
     * 删除个人信息以及对应的用户个人信息，若真实信息不存在，只删除个人信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteFarmer")
    public ResponseResult deleteFarmer(Integer id){
        int delete = farmerService.deleteFarmer(id);
        if(delete==1){
            return new ResponseResult(200,"删除农户信息成功");
        }else if(delete==0){
            return new ResponseResult(500,"删除农户信息失败");
        }else {
            return new ResponseResult(500,"农户已逻辑删除");
        }
    }

    /**
     * 通过id获取农户信息
     * @param id
     * @return
     */
    @GetMapping("selectFarmer/{id}")
    public ResponseResult selectFarmer(@PathVariable Integer id){
        ResponseResult selectFarmer = farmerService.selectFarmer(id);
        if(selectFarmer.getCode()==null){
            return new ResponseResult(200,"查询农户信息成功",selectFarmer);
        }else{
            return new ResponseResult(500,"查询农户信息失败");
        }
    }

    /**
     * 通过id获取农户全部信息
     * @param id
     * @return
     */
    @GetMapping("selectFarmerAll/{id}")
    public ResponseResult selectFarmerAll(@PathVariable Integer id){

        ResponseResult selectFarmer = farmerService.selectFarmer(id);
        if(selectFarmer.getCode()==null){
            ResponseResult selectFarmerAll = farmerService.selectFarmerAll((Farmer) selectFarmer.getData());
            if(selectFarmerAll.getCode()==null){
                return new ResponseResult(200,"查询农户信息成功",selectFarmerAll);
            }else{
                return new ResponseResult(500,"查询农户信息失败");
            }

        }else{
            return new ResponseResult(500,"查询农户信息失败");
        }


    }


    /**
     * 插入农户真实信息
     * @param farmer
     * @return
     */
    @PostMapping("insertFarmer")
    public ResponseResult insertFarmer(@RequestBody Farmer farmer){
        int insertFarmer = farmerService.insertFarmer(farmer);
        if(insertFarmer == 1){
            return new ResponseResult(200,"插入农户真实信息成功");
        }else{
            return new ResponseResult(500,"插入农户真实信息失败");
        }
    }
}
