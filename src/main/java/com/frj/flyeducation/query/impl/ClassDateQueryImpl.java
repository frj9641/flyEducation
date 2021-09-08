package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.classdate.ClassDate;
import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.mapper.ClassDateMapper;
import com.frj.flyeducation.query.ClassDateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassDateQueryImpl implements ClassDateQuery {
    @Autowired
    private ClassDateMapper classDateMapper;
    /**
     * 通过班级id查询返回班级上课时间
     * @param classId
     * @return
     */
    @Override
    public CommonResult queryClassDateByClassId(Integer classId) {
        List<ClassDate> classDates = classDateMapper.selectClassDateByClassId(classId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates=new ArrayList<>();
        for(int x=0;x<classDates.size();x++){
            String date = simpleDateFormat.format(classDates.get(x).getDate());
            dates.add(date);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("dates",dates);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
