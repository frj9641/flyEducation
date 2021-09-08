package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.score.Score;
import com.frj.flyeducation.domain.entity.score.ScoreRegistryBean;
import com.frj.flyeducation.domain.mapper.HomeworkMapper;
import com.frj.flyeducation.domain.mapper.ScoreMapper;
import com.frj.flyeducation.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Value("${scoreUrl}")
    private String scoreUrl;
    /**
     * 上传作业
     * @param score
     */
    @Override
    public CommonResult createScore(Score score) {
        Integer scoreId = scoreMapper.selectScoreIdByScore(score);
        Map<String,Object> map=new HashMap<>();
        if(scoreId==null){
            Integer classId = homeworkMapper.selectHomeworkByHomeworkId(score.getHomeworkId()).getClassId();
            score.setClassId(classId);
            scoreMapper.insertScore(score);
            map.put("msg","提交成功！");
            CommonResult commonResult = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
            return commonResult;
        }else{
            map.put("msg","您已提交过这项作业！");
            CommonResult commonResult = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
            return commonResult;
        }

    }

    /**
     * 上传作业照片
     * @param file
     */
    @Override
    public void uploadPhoto(MultipartFile file) {
        String path = scoreUrl;
        String filename = file.getOriginalFilename();
        File filePath = new File(path, filename);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据考核主键修改作业评分
     * @param scoreRegistryBean
     */
    @Override
    public void changeMarkByScoreId(ScoreRegistryBean scoreRegistryBean) {
        scoreMapper.updateMarkByScoreId(scoreRegistryBean);
    }
}
