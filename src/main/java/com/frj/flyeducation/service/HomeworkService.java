package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.homework.Homework;
import org.springframework.web.multipart.MultipartFile;

public interface HomeworkService {
    /**
     * 上传作业
     * @param homework
     */
    CommonResult createHomework(Homework homework);

    /**
     * 上传作业照片
     * @param file
     */
    void uploadPhoto(MultipartFile file);

    /**
     * 通过作业主键删除作业
     * @param homeworkId
     */
    void removeHomeworkByHomeworkId(Integer homeworkId);
}
