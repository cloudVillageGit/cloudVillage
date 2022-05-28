package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Address;
import com.cloudVillage.mapper.AddressMapper;
import com.cloudVillage.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    /**
     * 更新地址
     * @param address
     * @return
     */
    @PutMapping("updateAddress")
    public ResponseResult updateAddress( @RequestBody Address address){
        Integer update = addressService.updateAddress(address);
        if(update==0){
            return new ResponseResult(500,"更新地址失败");
        }else{
            return new ResponseResult(200,"更新地址成功");
        }
    }

    /**
     * 插入地址
     * @param address
     * @return
     */
    @PostMapping("insertAddress")
    public ResponseResult insertAddress(@RequestBody Address address){
        Integer insert = addressService.insertAddress(address);
        if(insert==0){
            return new ResponseResult(500,"插入地址失败");
        }else{
            return new ResponseResult(200,"插入地址成功");
        }
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @DeleteMapping("deleteAddress")
    public ResponseResult deleteAddress(@RequestBody Integer id){
        Integer delete = addressService.deleteAddress(id);
        if(delete==0){
            return new ResponseResult(500,"插入地址失败");
        }else{
            return new ResponseResult(200,"插入地址成功");
        }
    }

    @GetMapping("selectAddress/{id}")
    public ResponseResult selectAddress(@PathVariable Integer id){
        ResponseResult address = addressService.selectAddress(id);
        if(address.getCode()==null){
            return new ResponseResult(200,"查询地址信息成功",address);
        }else{
            return new ResponseResult(500,"查询地址信息失败");
        }
    }
}
