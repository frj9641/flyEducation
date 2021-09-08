package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.score.Score;
import com.frj.flyeducation.domain.entity.score.ScoreRegistryBean;
import com.frj.flyeducation.query.ScoreQuery;
import com.frj.flyeducation.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ScoreQuery scoreQuery;

    /**
     * 上传作业
     * @param score
     */
    @PostMapping
    public CommonResult createScore(Score score){
        return scoreService.createScore(score);
    }

    /**
     * 上传作业照片
     * @param file
     */
    @PostMapping(value = "/photo")
    public void uploadPhoto(@RequestParam("photo") MultipartFile file){
        scoreService.uploadPhoto(file);
    }

    /**
     * 根据作业id查询返回作业提交情况
     * @param homeworkId
     * @return
     */
    @GetMapping
    public CommonResult queryScoresByHomeworkId(@RequestParam("homeworkId") Integer homeworkId){
        return scoreQuery.queryScoresByHomeworkId(homeworkId);
    }

    /**
     * 根据考核主键返回作业内容
     * @param scoreId
     * @return
     */
    @GetMapping(value = "/mark")
    public CommonResult queryScoreByScoreId(@RequestParam("scoreId") Integer scoreId){
        return scoreQuery.queryScoreByScoreId(scoreId);
    }

    /**
     * 根据考核主键修改作业评分
     * @param scoreRegistryBean
     */
    @PutMapping(value = "/mark")
    public void changeMarkByScoreId(ScoreRegistryBean scoreRegistryBean){
        scoreService.changeMarkByScoreId(scoreRegistryBean);
    }
}
