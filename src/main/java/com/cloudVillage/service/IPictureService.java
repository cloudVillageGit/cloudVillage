package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IPictureService extends IService<Picture> {
    public ResponseResult selectByCharNameAndCharsId(String charsName,Integer charsId);
}
