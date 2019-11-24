package com.anyview.service.impl;

import com.anyview.dao.ClassAndStudentDao;
import com.anyview.service.ClassAndStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-25 20:54
 */
@Service
public class ClassAndStudentServiceImpl implements ClassAndStudentService {

    private final ClassAndStudentDao classAndStudentDao;
    @Autowired
    public ClassAndStudentServiceImpl(ClassAndStudentDao classAndStudentDao) {
        this.classAndStudentDao = classAndStudentDao;
    }

    @Override
    public Integer getClassIdByStudentId(Long studentId) {
        return classAndStudentDao.getClassIdByStudentId(studentId.intValue());
    }
}
