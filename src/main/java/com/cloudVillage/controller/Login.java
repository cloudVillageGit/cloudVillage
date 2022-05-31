package com.cloudVillage.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.User;
import com.cloudVillage.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  测试控制器
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/api")
public class Login {

    @Autowired
    private UserServiceImpl loginService;

    /**
     * 判断用户是否注册
     * @param code wx.login获取
     * @param avatarUrl wx.getUserProfile获取 用于存储用户头像
     * @param nickName wx.getUserProfile获取 用于储存用户昵称
     * @return
     */
    @PostMapping("Login")
    public ResponseResult decodeOpenid(@RequestParam("code") String code,@RequestParam("avatarUrl")String avatarUrl,@RequestParam("nickName")String nickName) {

        String appId = "wx123be454dd245a7a";
        String appSecret = "93ad002ca51fcd016b9675c1b0cc4dda";
        // 对指定网站发送请求
        HttpUtil httpUtil = new HttpUtil();
        String url = "https://api.weixin.qq.com/sns/jscode2session" + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code +
                "&grant_type=authorization_code";

        String s = httpUtil.get(url);

        // 对返回json数据 解析为对象
        JSONObject jsonObject = JSONUtil.parseObj(s);
        System.out.println(jsonObject.toString());
        // 获取session_key
        Object session_key = jsonObject.get("session_key");
        // 获取openId
        Object openId = jsonObject.get("openid");
        System.out.println(openId);

        JSONObject _jsonObject = new JSONObject();

        if(openId != null){
            // 生成登录凭证token，这里使用时间戳，实际开发中推荐用更成熟的机制生成token
            String token = "token_" + new Date().getTime();

            // 将openId和token传递到服务层，做下一步业务处理

            // 获取用户登录信息
            ResponseResult<User> dataPojo = loginService.login((String)openId, token,avatarUrl,nickName);

            System.out.println("成功获取用户授权信息");
            // 将获取返回的数据加入统一风格的json响应中
            return new ResponseResult(200,"成功获取用户授权信息",dataPojo);
        }else{
            System.out.println("无法获取用户授权信息");
//            out.print(new Gson().toJson(new BaseDataPojo<LoginSession>("无法获取用户授权信息", false, null)));

            return new ResponseResult<User>(500,"无法获取用户授权信息",null);
        }
    }

    /**
     * 查询token是否还有效
     * @param userToken 从前端返回的token
     */
    @PostMapping("CheckLogin")
    public ResponseResult<User> CheckLoginToken(@RequestParam("token")String userToken){
        int judge = loginService.checkToken(userToken);
        if(judge != -1){		// token有效
            return new ResponseResult(200,"token有效",judge);
        }else{			// token无效
            return new ResponseResult(500,"token无效",null);
        }
    }
}
