package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.school.School;
import com.frj.flyeducation.domain.entity.sign.Sign;
import com.frj.flyeducation.domain.mapper.SchoolMapper;
import com.frj.flyeducation.domain.mapper.SignMapper;
import com.frj.flyeducation.query.SignQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignQueryImpl implements SignQuery {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 通过学生id与班级id返回考核统计
     * @param studentId
     * @return
     */
    @Override
    public CommonResult querySignsAndScoresByStudentId(Integer studentId) {
        List<Sign> signs = signMapper.selectSignsByStudentId(studentId);
        List<School> schools = schoolMapper.selectSchoolsByStudentId(studentId);
        Map<String,Object> map=new HashMap<>();
        map.put("signs",signs);
        map.put("scores",schools);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
