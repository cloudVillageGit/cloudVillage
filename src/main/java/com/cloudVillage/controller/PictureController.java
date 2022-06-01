package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.service.IPictureService;
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
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private IPictureService pictureService;

    @PostMapping("selectPicture")
    public ResponseResult selectPicture(@RequestParam String charsName,@RequestParam Integer charsId) {
        ResponseResult responseResult = pictureService.selectByCharNameAndCharsId(charsName, charsId);
        if(responseResult.getCode() == 500){
            return new ResponseResult(500,"图片未找到");
        }else{
            return new ResponseResult(200,"图片查找成功",responseResult.getData());
        }
    }
}
