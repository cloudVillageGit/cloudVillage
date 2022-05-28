package com.cloudVillage.controller;


import com.cloudVillage.config.ResponseResult;
import com.cloudVillage.entity.Evaluate;
import com.cloudVillage.mapper.EvaluateMapper;
import com.cloudVillage.service.IEvaluateService;
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
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private IEvaluateService evaluateService;

    /**
     * 插入评论
     * @param evaluate
     * @return
     */
    @PostMapping("insertEvaluate")
    public ResponseResult insertEvaluate(@RequestBody Evaluate evaluate){
        Integer insert = evaluateService.insertEvaluate(evaluate);
        if(insert==0){
            return new ResponseResult(500,"插入评论失败");
        }else{
            return new ResponseResult(200,"插入评论成功");
        }
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("deleteEvaluate")
    public ResponseResult deleEvaluate(@RequestParam Integer id) {
        Integer delete = evaluateService.deleteEvaluate(id);
        if(delete==0){
            return new ResponseResult(500,"删除评论失败");
        }else{
            return new ResponseResult(200,"删除评论成功");
        }
    }

    /**
     * 通过id返回评论json
     * @param id
     * @return
     */
    @GetMapping("searchEvaluate/{id}")
    public ResponseResult searchEvaluate(@PathVariable Integer id){
        ResponseResult searchList = evaluateService.searchEvaluate(id);
        if(searchList.getCode()==null){
            return new ResponseResult(500,"查询评论失败");
        }else {
            return new ResponseResult(200, "查询评论成功", searchList);
        }
    }

}
