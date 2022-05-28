package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.cloudVillage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * 修改个人信息
     */
    @PutMapping("updateUserInfo")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        Integer update = userService.updateUserInfo(user);
        if(update==0){
            return new ResponseResult(500,"更新用户信息失败");
        }else{
            return new ResponseResult(200,"更新用户信息成功");
        }
    }

    /**
     * 删除个人信息以及对应的用户个人信息，若真实信息不存在，只删除个人信息
     * @param id
     * @return
     */

    @DeleteMapping("deleteUserInfoAndRealInfo")
    public ResponseResult deleteUser(Integer id){
        ResponseResult delete = userService.deleteUser(id);
        if(delete.getCode() == 500){
            return new ResponseResult(500,"删除失败");
        }else{
            return new ResponseResult(200,"删除成功");
        }
    }

    /**
     * 获取单个用户的所有信息，包括真实信息
     */
    @PostMapping("oneUserInfo")
    public ResponseResult getOneUserInfo(@RequestParam Integer id){
        ResponseResult oneUserAllInfo = userService.getOneUserAllInfo(id);
        if(oneUserAllInfo.getCode() == null){
            return new ResponseResult(200,"查询成功",oneUserAllInfo);
        }else{
            return new ResponseResult(500,"查询失败，无数据");
        }
    }

    /**
     * 获取所有用户的所有信息，包括真实信息
     */
    @PostMapping("allUserInfo")
    public ResponseResult getAllUserInfo(){
        ResponseResult allUserAllInfo = userService.getAllUserAllInfo();
        if(allUserAllInfo.getCode() == null){
            return new ResponseResult(200,"查询成功",allUserAllInfo);

        }else{
            return new ResponseResult(500,"查询失败，无数据");
        }
    }

}
