package com.cloudVillage.controller;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.FarmerLogin;
import com.cloudVillage.service.IFarmerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/15:26
 * @Description:
 */
@RestController
@RequestMapping("/FBE")
public class FarmerLoginController {
    @Autowired
    private IFarmerLoginService farmerLoginService;

    @PostMapping("farmer/login")
    public ResponseResult loginFBE(@RequestParam String fName,@RequestParam String fPassword){
        ResponseResult responseResult = farmerLoginService.selectFarmerLogin(fName, fPassword);

        if(responseResult.getCode() == null){
            Integer farmId = (Integer) responseResult.getData();
            return new ResponseResult(200,"登陆成功",farmId);
        }
        else if(responseResult.getCode() == 606){
            return new ResponseResult(500,"无此用户");
        }else if(responseResult.getCode() == 607){
            return new ResponseResult(500,"用户密码错误");
        }else{
            return null;
        }
    }
}
