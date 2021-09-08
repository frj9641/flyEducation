package com.frj.flyeducation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frj.flyeducation.domain.entity.common.TemplateMessageResult;
import com.frj.flyeducation.domain.entity.sign.Sign;
import com.frj.flyeducation.domain.entity.sign.SignReminderBean;
import com.frj.flyeducation.domain.entity.student.Student;
import com.frj.flyeducation.domain.mapper.AccountMapper;
import com.frj.flyeducation.domain.mapper.SignMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.domain.mapper.SubscriberMapper;
import com.frj.flyeducation.service.SignService;
import com.frj.flyeducation.util.AccessTokenUtil;
import com.frj.flyeducation.util.TemplateMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SubscriberMapper subscriberMapper;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 提交签到记录
     * @param sign
     */
    @Transactional
    @Override
    public void createSign(Sign sign) {
        signMapper.insertSign(sign);
        Integer orderId = studentMapper.selectOrderIdByStudentId(sign.getStudentId());
        Integer isPeriod = accountMapper.selectIsPeriodByOrderId(orderId);
        if(isPeriod==1&&sign.getMark()!=3){
            Integer period = accountMapper.selectPeriodByOrderId(orderId);
            accountMapper.updatePeriodByOrderId(orderId);
            Student student = studentMapper.selectStudentByStudentId(sign.getStudentId());
            SignReminderBean signReminderBean = SignReminderBean.builder().customerUnionid(student.getCustomerUnionid())
                    .studentName(student.getStudentName()).className(student.getClassName())
                    .date(sign.getDate()).consume(1).last(period - 1).build();
            periodReminder(signReminderBean);
        }
    }

    /**
     * 课时扣除提醒
     * @param signReminderBean
     */
    private void periodReminder(SignReminderBean signReminderBean){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String accessToken = AccessTokenUtil.getAccessToken();
        String openid = subscriberMapper.selectOfficalOpenidByUnionid(signReminderBean.getCustomerUnionid());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);
        jsonObject.put("template_id", "xxx");
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();
        JSONObject keyword3 = new JSONObject();
        JSONObject keyword4 = new JSONObject();
        JSONObject keyword5 = new JSONObject();
        JSONObject remark = new JSONObject();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("keyword5", keyword5);
        data.put("remark", remark);
        first.put("value", "家长你好,您孩子的课时扣除通知");
        keyword1.put("value", signReminderBean.getStudentName());
        keyword2.put("value", signReminderBean.getClassName());
        keyword3.put("value", simpleDateFormat.format(signReminderBean.getDate()));
        keyword4.put("value", signReminderBean.getConsume());
        keyword5.put("value", signReminderBean.getLast());
        remark.put("value","感谢您的支持,系统消息,无需回复。");
        jsonObject.put("data", data);
        String result = jsonObject.toJSONString();
        restTemplate.postForEntity(TemplateMessageUtil.TEMPLATE_MESSAGE_URL + accessToken, result, TemplateMessageResult.class);
    }
}
