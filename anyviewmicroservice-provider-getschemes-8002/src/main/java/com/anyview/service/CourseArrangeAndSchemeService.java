package com.anyview.service;

import com.anyview.entity.CourseArrangeAndScheme;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-25 21:04
 */
public interface CourseArrangeAndSchemeService {


    List<CourseArrangeAndScheme> getByClassId(Integer classId);
}
