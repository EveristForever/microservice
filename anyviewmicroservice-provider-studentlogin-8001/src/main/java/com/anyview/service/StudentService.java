package com.anyview.service;

import com.anyview.entity.Student;

import java.util.List;

public interface StudentService {
    //以下为学生端接口方法

    //根据学生id查询学生
    Student getById(Long id);

    void updatePwdById(Long id, String password);

    List<Student> getStudentInfo(String sNo, Integer schoolId);

    //修改登录状态
    void setLoginStatusSuccess(String sNo,Integer schoolId, String ip, int port);

    //登出
    void updateLoginState(Long studentId);
}
