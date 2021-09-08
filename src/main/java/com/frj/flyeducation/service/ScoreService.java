package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.score.Score;
import com.frj.flyeducation.domain.entity.score.ScoreRegistryBean;
import org.springframework.web.multipart.MultipartFile;

public interface ScoreService {
    /**
     * 上传作业
     * @param score
     */
    CommonResult createScore(Score score);

    /**
     * 上传作业照片
     * @param file
     */
    void uploadPhoto(MultipartFile file);

    /**
     * 根据考核主键修改作业评分
     * @param scoreRegistryBean
     */
    void changeMarkByScoreId(ScoreRegistryBean scoreRegistryBean);
}
