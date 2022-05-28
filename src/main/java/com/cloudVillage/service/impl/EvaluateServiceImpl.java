package com.cloudVillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Evaluate;
import com.cloudVillage.mapper.EvaluateMapper;
import com.cloudVillage.service.IEvaluateService;
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
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements IEvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public Integer insertEvaluate(Evaluate evaluate) {
        int insert = evaluateMapper.insert(evaluate);
        return insert;
    }

    @Override
    public Integer updateEvaluate(Evaluate evaluate) {
        UpdateWrapper<Evaluate> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",evaluate.getId());
        int update = evaluateMapper.update(evaluate, updateWrapper);
        return update;
    }

    @Override
    public Integer deleteEvaluate(Integer id) {
        int delete = evaluateMapper.deleteById(id);
        return delete;
    }

    @Override
    public ResponseResult searchEvaluate(Integer userId) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        List<Evaluate> evaluates = evaluateMapper.selectList(queryWrapper);
        if(evaluates.size()==0){
            return new ResponseResult(500,"查询评论失败");
        }
        return new ResponseResult(evaluates);
    }


}
