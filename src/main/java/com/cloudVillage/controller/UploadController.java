package com.cloudVillage.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/3:34
 * @Description:
 */

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.service.UploadImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

@Slf4j
@Controller
public class UploadController {

    @Resource
    UploadImageService uploadImageService;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }


    @PostMapping(value = "/image")
    @ResponseBody
    private ResponseResult upLoadImage(@RequestParam("file") MultipartFile file) {
        //System.out.println(file);
        if (!file.isEmpty()) {
            String path = uploadImageService.uploadQNImg(file);
        }
        return new ResponseResult(200);
    }

    /*
     * 这里为了方便测试才这么写的 可以根据实际需要 自己写。
     * @Param
     * @return java.lang.String
     **/
    @ResponseBody
    @DeleteMapping("/remove")
    public String removeFile(@RequestParam String fileKey){
        uploadImageService.removeFile("cloudtestimg",fileKey);
        return "删除成功";
    }
}
