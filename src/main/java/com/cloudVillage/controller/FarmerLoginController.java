package com.cloudVillage.controller;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.FarmerLogin;
import com.cloudVillage.entity.User;
import com.cloudVillage.service.IFarmerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Date;

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
        String token = "FT" +  new Date().getTime();
        ResponseResult responseResult = farmerLoginService.selectFarmerLogin(fName, fPassword,token);
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
    /**
     * 查询token是否还有效
     * @param farmerToken 从前端返回的token
     */
    @PostMapping("CheckLoginToken")
    public ResponseResult<FarmerLogin> checkFarmerLoginToken(@RequestParam("token")String farmerToken){
        int judge = farmerLoginService.checkFarmerToken(farmerToken);
        if(judge != -1){		// token有效
            return new ResponseResult<FarmerLogin>(200,"token有效",null);
        }else{			// token无效
            return new ResponseResult<FarmerLogin>(500,"token无效",null);
        }
    }
}
