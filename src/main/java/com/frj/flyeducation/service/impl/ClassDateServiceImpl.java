package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frj.flyeducation.domain.entity.classdate.ClassDate;
import com.frj.flyeducation.domain.entity.classdate.ClassDateChangeBean;
import com.frj.flyeducation.domain.entity.classdate.ClassDateRegistryBean;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.common.TemplateMessageResult;
import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.mapper.ClassDateMapper;
import com.frj.flyeducation.domain.mapper.ClassInfoMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.domain.mapper.SubscriberMapper;
import com.frj.flyeducation.service.ClassDateService;
import com.frj.flyeducation.util.AccessTokenUtil;
import com.frj.flyeducation.util.TemplateMessageUtil;
import com.frj.flyeducation.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClassDateServiceImpl implements ClassDateService {
    @Autowired
    private ClassDateMapper classDateMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SubscriberMapper subscriberMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 批量创建课程日期
     * @param classDateRegistryBean
     */
    @Override
    public void createClassDate(ClassDateRegistryBean classDateRegistryBean) {
        List<Date> dates = classDateRegistryBean.getDates();
        String time=classDateRegistryBean.getStartHour()+":"+classDateRegistryBean.getStartMinute()
                +"~"+classDateRegistryBean.getEndHour()+":"+classDateRegistryBean.getEndMinute();
        for(int x=0;x<dates.size();x++){
            ClassDate classDate = ClassDate.builder().classId(classDateRegistryBean.getClassId()).date(dates.get(x)).time(time).build();
            classDateMapper.insertClassDate(classDate);
        }
    }

    /**
     * 修改上课时间
     * @param classDateChangeBean
     * @return
     */
    @Override
    public CommonResult changeDateByClassId(ClassDateChangeBean classDateChangeBean) {
        String oldTime=classDateMapper.selectTimeByDate(classDateChangeBean);
        classDateMapper.updateDateByClassId(classDateChangeBean);
        String time=classDateChangeBean.getStartHour()+":"+classDateChangeBean.getStartMinute()
                +"~"+classDateChangeBean.getEndHour()+":"+classDateChangeBean.getEndMinute();
        String r = dateChangeReminder(classDateChangeBean,time,oldTime);
        Map<String,Object> map=new HashMap<>();
        map.put("result",r);
        CommonResult result = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
        return result;
    }

    /**
     * 增加上课时间
     * @param classDateRegistryBean
     * @return
     */
    @Override
    public CommonResult addDateByClassId(ClassDateRegistryBean classDateRegistryBean) {
        Date date=classDateRegistryBean.getDates().get(0);
        String time=classDateRegistryBean.getStartHour()+":"+classDateRegistryBean.getStartMinute()
                +"~"+classDateRegistryBean.getEndHour()+":"+classDateRegistryBean.getEndMinute();
        ClassDate classDate = ClassDate.builder().classId(classDateRegistryBean.getClassId())
                .date(date).time(time).build();
        classDateMapper.insertClassDate(classDate);
        String r = dateAddReminder(classDateRegistryBean,time);
        Map<String,Object> map=new HashMap<>();
        map.put("result",r);
        CommonResult result = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
        return result;
    }

    /**
     * 每日上课提醒
     */
    @Override
    public void classDateReminder() {
        String accessToken= AccessTokenUtil.getAccessToken();
        List<ClassDate> classDates = classDateMapper.selectClassDatesByDate();
        for(int x=0;x<classDates.size();x++){
            List<Student> students = studentMapper.selectStudentsByClassId(classDates.get(x).getClassId());
            String time = classDates.get(x).getTime();
            for(int y=0;y<students.size();y++){
                String openid = subscriberMapper.selectOfficalOpenidByUnionid(students.get(y).getCustomerUnionid());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("touser", openid);
                jsonObject.put("template_id", "xxx");
                JSONObject data = new JSONObject();
                JSONObject first = new JSONObject();
                JSONObject keyword1 = new JSONObject();
                JSONObject keyword2 = new JSONObject();
                JSONObject keyword3 = new JSONObject();
                JSONObject remark = new JSONObject();
                data.put("first", first);
                data.put("keyword1", keyword1);
                data.put("keyword2", keyword2);
                data.put("keyword3", keyword3);
                data.put("remark", remark);
                first.put("value", "上课提醒");
                keyword1.put("value", students.get(y).getClassName());
                keyword2.put("value", TimeUtil.getTomorrow()+" "+time);
                keyword3.put("value", "博佳俱乐部");
                remark.put("value", "请做好课前预习！");
                jsonObject.put("data", data);
                String result = jsonObject.toJSONString();
                restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
            }
        }
    }

    /**
     * 上课时间修改家长提醒
     * @param classDateChangeBean
     * @return
     */
    private String dateChangeReminder(ClassDateChangeBean classDateChangeBean,String time,String oldTime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Student> students = studentMapper.selectStudentsByClassId(classDateChangeBean.getClassId());
        String className = students.get(0).getClassName();
        String accessToken = AccessTokenUtil.getAccessToken();
        List<String> failure=new ArrayList<>();
        for(int x=0;x<students.size();x++){
            String openid = subscriberMapper.selectOfficalOpenidByUnionid(students.get(x).getCustomerUnionid());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", openid);
            jsonObject.put("template_id", "xxx");
            JSONObject data = new JSONObject();
            JSONObject first = new JSONObject();
            JSONObject keyword1 = new JSONObject();
            JSONObject keyword2 = new JSONObject();
            JSONObject remark = new JSONObject();
            data.put("first", first);
            data.put("keyword1", keyword1);
            data.put("keyword2", keyword2);
            data.put("remark", remark);
            first.put("value", "课程调整");
            keyword1.put("value", simpleDateFormat.format(classDateChangeBean.getOldDay())+" "+oldTime);
            keyword2.put("value", simpleDateFormat.format(classDateChangeBean.getNewDay())+" "+time);
            remark.put("value", "您好，由于课程调整，您报名的课程 "+className+" 将改期举行，给您带来的不便非常抱歉，敬请谅解。");
            jsonObject.put("data", data);
            String result = jsonObject.toJSONString();
            ResponseEntity<TemplateMessageResult> templateMessageResultResponseEntity = restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
            String errcode = templateMessageResultResponseEntity.getBody().getErrcode();
            if (!errcode.equals("0")) {
                failure.add(students.get(x).getStudentName());
            }
        }

        String teacherUnionid = classInfoMapper.selectTeacherUnionidByClassId(classDateChangeBean.getClassId());
        String openid = subscriberMapper.selectOfficalOpenidByUnionid(teacherUnionid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);
        jsonObject.put("template_id", "xxx");
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();
        JSONObject remark = new JSONObject();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("remark", remark);
        first.put("value", "课程调整");
        keyword1.put("value", simpleDateFormat.format(classDateChangeBean.getOldDay())+" "+oldTime);
        keyword2.put("value", simpleDateFormat.format(classDateChangeBean.getNewDay())+" "+time);
        remark.put("value", "您好，由于课程调整，您教授的课程 "+className+" 将改期举行，给您带来的不便非常抱歉，敬请谅解。");
        jsonObject.put("data", data);
        String result = jsonObject.toJSONString();
        restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);

        if(failure.isEmpty()){
            return "家长提醒成功！";
        }else{
            return failure.toString()+"家长提醒未成功！";
        }
    }
    /**
     * 上课时间增加家长提醒
     * @param classDateRegistryBean
     * @return
     */
    private String dateAddReminder(ClassDateRegistryBean classDateRegistryBean,String time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Student> students = studentMapper.selectStudentsByClassId(classDateRegistryBean.getClassId());
        String className = students.get(0).getClassName();
        String accessToken = AccessTokenUtil.getAccessToken();
        List<String> failure=new ArrayList<>();
        for(int x=0;x<students.size();x++){
            String openid = subscriberMapper.selectOfficalOpenidByUnionid(students.get(x).getCustomerUnionid());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", openid);
            jsonObject.put("template_id", "xxx");
            JSONObject data = new JSONObject();
            JSONObject first = new JSONObject();
            JSONObject keyword1 = new JSONObject();
            JSONObject keyword2 = new JSONObject();
            JSONObject keyword3 = new JSONObject();
            JSONObject remark = new JSONObject();
            data.put("first", first);
            data.put("keyword1", keyword1);
            data.put("keyword2", keyword2);
            data.put("keyword3", keyword3);
            data.put("remark", remark);
            first.put("value", "补课通知");
            keyword1.put("value", "飞扬教育");
            keyword2.put("value", className);
            keyword3.put("value", simpleDateFormat.format(classDateRegistryBean.getDates().get(0))+" "+time);
            remark.put("value", "老师为您额外安排了课程，请准时上课！");
            jsonObject.put("data", data);
            String result = jsonObject.toJSONString();
            ResponseEntity<TemplateMessageResult> templateMessageResultResponseEntity = restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
            String errcode = templateMessageResultResponseEntity.getBody().getErrcode();
            if (!errcode.equals("0")) {
                failure.add(students.get(x).getStudentName());
            }
        }

        String teacherUnionid = classInfoMapper.selectTeacherUnionidByClassId(classDateRegistryBean.getClassId());
        String openid = subscriberMapper.selectOfficalOpenidByUnionid(teacherUnionid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);
        jsonObject.put("template_id", "xxx");
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();
        JSONObject keyword3 = new JSONObject();
        JSONObject remark = new JSONObject();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("remark", remark);
        first.put("value", "补课通知");
        keyword1.put("value", "飞扬教育");
        keyword2.put("value", className);
        keyword3.put("value", simpleDateFormat.format(classDateRegistryBean.getDates().get(0))+" "+time);
        remark.put("value", "教务为您额外安排了课程，请准时上课！");
        jsonObject.put("data", data);
        String result = jsonObject.toJSONString();
        restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);

        if(failure.isEmpty()){
            return "家长提醒成功！";
        }else{
            return failure.toString()+"家长提醒未成功！";
        }
    }
}
