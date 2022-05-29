package com.cloudVillage.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/3:34
 * @Description:
 */

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
    private String upLoadImage(@RequestParam("file") MultipartFile file, Model model) {

        if (!file.isEmpty()) {
            String path = uploadImageService.uploadQNImg(file);
            System.out.print("七牛云返回的图片链接:" + path);
            model.addAttribute("link",path);
            return "upload";
        }
        return "";
    }

    /*
     * 这里为了方便测试才这么写的 可以根据实际需要 自己写。
     * @Param
     * @return java.lang.String
     **/
    @ResponseBody
    @DeleteMapping("/remove")
    public String removeFile(){
        uploadImageService.removeFile("wyhdsg","0b677574afdf4a8ea1271108f1176bde.jpg");
        return "删除成功";
    }

}
