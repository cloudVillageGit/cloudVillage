package com.cloudVillage.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/05/29/3:32
 * @Description:
 */
import org.springframework.web.multipart.MultipartFile;


public interface UploadImageService {

    String uploadQNImg(MultipartFile file);

    String getPrivateFile(String fileKey);

    boolean removeFile(String bucketName, String fileKey);

}
