package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Picture;
import com.cloudVillage.mapper.PictureMapper;
import com.cloudVillage.service.IPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements IPictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public ResponseResult selectByCharNameAndCharsId(String charsName, Integer charsId) {
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("charsId",charsId);
        pictureQueryWrapper.eq("chartsName",charsName);
        List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);
        if(pictures.size() == 0){
            return new ResponseResult(500,"未找到");
        }else{
            return new ResponseResult(200,"图片已找到",pictures.get(0));
        }
    }

    @Override
    public ResponseResult deleteByChartNameAndId(String chartName, Integer chartId) {
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("chartsName",chartName);
        pictureQueryWrapper.eq("charsId",chartId);
        List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);
        if(pictures.size() == 0){
            return new ResponseResult(500,"删除失败");
        }else{
            return new ResponseResult(200,"删除成功");
        }
    }

    @Override
    public ResponseResult insertPicture(Picture picture) {
        return null;
    }
}
