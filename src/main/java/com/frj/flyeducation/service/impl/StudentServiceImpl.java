package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.order.Order;
import com.frj.flyeducation.domain.entity.student.StudentClassChangeBean;
import com.frj.flyeducation.domain.entity.student.StudentCustomizeRegistryBean;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.domain.mapper.OrderMapper;
import com.frj.flyeducation.domain.mapper.StudentMapper;
import com.frj.flyeducation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 通过学生主键设置班级序号或置空班级序号
     * @param studentClassChangeBean
     */
    @Override
    public void changeClassIdAndClassNameByStudentId(StudentClassChangeBean studentClassChangeBean) {
        studentMapper.updateClassIdAndClassNameByStudentId(studentClassChangeBean);
    }

    /**
     * 注册自定义学员
     * @param studentCustomizeRegistryBean
     */
    @Override
    public CommonResult createCustomizeStudent(StudentCustomizeRegistryBean studentCustomizeRegistryBean) {
        Market market = marketMapper.selectMarketByMarketId(studentCustomizeRegistryBean.getMarketId());
        Order order = Order.builder().customerUnionid(studentCustomizeRegistryBean.getCustomerUnionid())
                .studentName(studentCustomizeRegistryBean.getStudentName()).parentName(studentCustomizeRegistryBean.getParentName())
                .school(studentCustomizeRegistryBean.getSchool()).grade(studentCustomizeRegistryBean.getGrade())
                .phone(studentCustomizeRegistryBean.getPhone()).marketId(studentCustomizeRegistryBean.getMarketId())
                .registrarUnionid(market.getRegistrarUnionid()).build();
        order.setIsAdded(1);
        order.setIsFinished(1);
        order.setIsIntent(1);
        order.setIsSuccess(1);
        orderMapper.insertOrder(order);
        Integer orderId = order.getOrderId();
        Map<String,Object> map=new HashMap<>();
        map.put("orderId",orderId);
        CommonResult result = CommonResult.builder().code(200).msg("处理成功").ext(map).build();
        return result;
    }

}
