package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.TemplateMessageResult;
import com.frj.flyeducation.domain.entity.homework.Homework;
import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.mapper.HomeworkMapper;
import com.frj.flyeducation.domain.mapper.ScoreMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.domain.mapper.SubscriberMapper;
import com.frj.flyeducation.service.HomeworkService;
import com.frj.flyeducation.util.AccessTokenUtil;
import com.frj.flyeducation.util.TemplateMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SubscriberMapper subscriberMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${photoUrl}")
    private String photoUrl;
    /**
     * 上传作业
     * @param homework
     */
    @Override
    public CommonResult createHomework(Homework homework) {
        Integer homeworkId = homeworkMapper.selectHomeworkIdByDate(homework.getClassId());
        Map<String,Object> map=new HashMap<>();
        if(homeworkId==null){
            homeworkMapper.insertHomework(homework);
            homeworkRemider(homework);
            map.put("msg","发布成功！");
            CommonResult commonResult = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
            return commonResult;
        }else{
            map.put("msg","您已发布过该班的今日作业！");
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
        String path = photoUrl;
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
     * 通过作业主键删除作业
     * @param homeworkId
     */
    @Transactional
    @Override
    public void removeHomeworkByHomeworkId(Integer homeworkId) {
        homeworkMapper.deleteHomeworkByHomeworkId(homeworkId);
        scoreMapper.deleteScoresByHomeworkId(homeworkId);
    }

    /**
     * 作业提醒
     * @param homework
     */
    private void homeworkRemider(Homework homework){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Student> students = studentMapper.selectStudentsByClassId(homework.getClassId());
        String className = students.get(0).getClassName();
        String accessToken = AccessTokenUtil.getAccessToken();
        for(int x=0;x<students.size();x++){
            String openid = subscriberMapper.selectOfficalOpenidByUnionid(students.get(x).getCustomerUnionid());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", openid);
            jsonObject.put("template_id", "xxx");
            JSONObject miniprogram = new JSONObject();
            miniprogram.put("appid","xxx");
            miniprogram.put("path","pages/customer/class/class");
            JSONObject data = new JSONObject();
            JSONObject first = new JSONObject();
            JSONObject keyword1 = new JSONObject();
            JSONObject keyword2 = new JSONObject();
            JSONObject remark = new JSONObject();
            data.put("first", first);
            data.put("keyword1", keyword1);
            data.put("keyword2", keyword2);
            data.put("remark", remark);
            first.put("value", "您好，您已经收到新作业了，请查收。");
            keyword1.put("value", 1);
            keyword2.put("value", className+simpleDateFormat.format(new Date())+"的作业");
            remark.put("value", "感谢您的查阅，请及时完成所有作业。您还可以点击下面的小程序跳转进行老师反馈！");
            jsonObject.put("data", data);
            jsonObject.put("miniprogram",miniprogram);
            String result = jsonObject.toJSONString();
            restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
        }
    }
}
