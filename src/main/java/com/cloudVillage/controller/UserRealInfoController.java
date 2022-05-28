package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.UserRealInfo;
import com.cloudVillage.service.IUserRealInfoService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户真实信息 前端控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/userRealInfo")
public class UserRealInfoController {
    @Autowired
    private IUserRealInfoService userRealInfoService;

    /**
     * 修改个人真实信息
     */
    @PutMapping("updateUserInfo")
    public ResponseResult updateUserInfo(@RequestBody UserRealInfo userRealInfo){
        int update = userRealInfoService.updateUserInfo(userRealInfo);
        if(update==0){
            return new ResponseResult(500,"更新用户真实信息失败");
        }else{
            return new ResponseResult(200,"更新用户真实信息成功");
        }
    }

    /**
     * 删除用户真实信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteUserInfo")
    public ResponseResult deleteUserInfo(@RequestParam Integer id){
        int deleteUserInfo = userRealInfoService.deleteUserInfo(id);
        if(deleteUserInfo==1){
            return new ResponseResult(200,"删除用户真实信息成功");
        }else if(deleteUserInfo==0){
            return new ResponseResult(500,"删除用户真实信息失败");
        }else {
            return new ResponseResult(500,"用户已逻辑删除");
        }
    }

    /**
     * 通过id获取用户真实信息
     * @param id
     * @return
     */
    @PostMapping("selectUserRealInfo")
    public ResponseResult selectUserRealInfo(@RequestParam Integer id){
        ResponseResult selectUserRealInfo = userRealInfoService.selectUserRealInfo(id);
        if(selectUserRealInfo.getCode()==null){
            return new ResponseResult(200,"查询用户真实信息成功",selectUserRealInfo);
        }else{
            return new ResponseResult(500,"查询用户真实信息失败");
        }
    }

    /**
     * 插入用户真实信息
     * @param userRealInfo
     * @return
     */
    @PostMapping("insertUserRealInfo")
    public ResponseResult insertUserRealInfo(@RequestBody UserRealInfo userRealInfo){
        int insertUserRealInfo = userRealInfoService.insertUserInfo(userRealInfo);
        if(insertUserRealInfo == 1){
            return new ResponseResult(200,"插入用户真实信息成功");
        }else{
            return new ResponseResult(500,"插入用户真实信息失败");
        }
    }
}
