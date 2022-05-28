package com.cloudVillage.service;

import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Evaluate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
public interface IEvaluateService extends IService<Evaluate> {
    public Integer insertEvaluate(Evaluate evaluate);
    public Integer updateEvaluate(Evaluate evaluate);
    public Integer deleteEvaluate(Integer id);
    public ResponseResult searchEvaluate(Integer userId);
}
