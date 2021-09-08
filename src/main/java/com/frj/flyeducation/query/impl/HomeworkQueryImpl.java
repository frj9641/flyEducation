package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.homework.Homework;
import com.frj.flyeducation.domain.entity.homework.HomeworkMarkQueryBean;
import com.frj.flyeducation.domain.entity.homework.HomeworkQueryResultBean;
import com.frj.flyeducation.domain.entity.homework.HomeworkResultBean;
import com.frj.flyeducation.domain.entity.score.ScoreResultBean;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.domain.mapper.HomeworkMapper;
import com.frj.flyeducation.domain.mapper.ScoreMapper;
import com.frj.flyeducation.query.HomeworkQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeworkQueryImpl implements HomeworkQuery {
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    /**
     * 通过班级id查询返回所有作业
     * @param classId
     * @return
     */
    @Override
    public CommonResult queryHomeworkByClassId(Integer classId) {
        List<Homework> homeworks = homeworkMapper.selectHomeworkByClassId(classId);
        List<HomeworkQueryResultBean> homeworkQueryResultBeans=new ArrayList<>();
        for(int i=0;i<homeworks.size();i++){
            String photoUrl = homeworks.get(i).getPhotoUrl();
            String[] urls = photoUrl.split(",");
            List<String> photoUrls = new ArrayList<>(Arrays.asList(urls));
            HomeworkQueryResultBean homeworkQueryResultBean = HomeworkQueryResultBean.builder().homeworkId(homeworks.get(i).getHomeworkId())
                    .date(homeworks.get(i).getDate()).photoUrl(photoUrls).build();
            homeworkQueryResultBeans.add(homeworkQueryResultBean);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("homeworks",homeworkQueryResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过作业主键查询返回作业内容
     * @param homeworkId
     * @return
     */
    @Override
    public CommonResult queryHomeworkByHomeworkId(Integer homeworkId) {
        Homework homework = homeworkMapper.selectHomeworkByHomeworkId(homeworkId);
        String photoUrl = homework.getPhotoUrl();
        List<String> photoUrls=new ArrayList<>();
        String[] urls = photoUrl.split(",");
        photoUrls.addAll(Arrays.asList(urls));
        HomeworkQueryResultBean homeworkQueryResultBean = HomeworkQueryResultBean.builder().homeworkId(homework.getHomeworkId())
                .classId(homework.getClassId()).word(homework.getWord()).photoUrl(photoUrls)
                .date(homework.getDate()).build();
        Map<String,Object> map=new HashMap<>();
        map.put("homework",homeworkQueryResultBean);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 通过学生id与班级id查询返回所有作业与评价
     * @param studentClassChangeBean
     * @return
     */
    @Override
    public CommonResult queryHomeworksAndMarksByStudentBean(StudentClassChangeBean studentClassChangeBean) {
        List<Homework> homeworks = homeworkMapper.selectHomeworkByClassId(studentClassChangeBean.getClassId());
        List<HomeworkResultBean> homeworkResultBeans=new ArrayList<>();
        for(int x=0;x<homeworks.size();x++){
            Integer homeworkId = homeworks.get(x).getHomeworkId();
            HomeworkMarkQueryBean homeworkMarkQueryBean = HomeworkMarkQueryBean.builder().homeworkId(homeworkId).studentId(studentClassChangeBean.getStudentId())
                    .build();
            ScoreResultBean scoreResultBean = scoreMapper.selectScoreByHomeworkMarkQueryBean(homeworkMarkQueryBean);

            if(scoreResultBean!=null){
                String mark = scoreResultBean.getMark();
                if(mark==null){
                    mark="未批改";
                }
                String photoUrl = scoreResultBean.getPhotoUrl();
                List<String> photoUrls=new ArrayList<>();
                String[] urls = photoUrl.split(",");
                photoUrls.addAll(Arrays.asList(urls));

                HomeworkResultBean homeworkResultBean = HomeworkResultBean.builder().scoreId(scoreResultBean.getScoreId())
                        .date(homeworks.get(x).getDate()).homeworkId(homeworks.get(x).getHomeworkId()).mark(mark).photoUrls(photoUrls).build();
                homeworkResultBeans.add(homeworkResultBean);
            }else{
                HomeworkResultBean homeworkResultBean = HomeworkResultBean.builder().date(homeworks.get(x).getDate())
                        .homeworkId(homeworks.get(x).getHomeworkId()).mark("未提交").photoUrls(new ArrayList<>()).build();
                homeworkResultBeans.add(homeworkResultBean);
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("homeworks",homeworkResultBeans);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
