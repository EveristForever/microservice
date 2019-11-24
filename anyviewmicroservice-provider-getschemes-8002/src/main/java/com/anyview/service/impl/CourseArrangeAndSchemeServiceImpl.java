package com.anyview.service.impl;

import com.anyview.dao.CourseArrangeAndSchemeDao;
import com.anyview.entity.CourseArrangeAndScheme;
import com.anyview.repository.CourseArrangeAndSchemeRepository;
import com.anyview.service.CourseArrangeAndSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-25 21:06
 */
@Service
public class CourseArrangeAndSchemeServiceImpl implements CourseArrangeAndSchemeService {

   // private final CourseArrangeAndSchemeRepository courseArrangeAndSchemeRepository;
    private final CourseArrangeAndSchemeDao courseArrangeAndSchemeDao;
    @Autowired
    public CourseArrangeAndSchemeServiceImpl(/*CourseArrangeAndSchemeRepository courseArrangeAndSchemeRepository, */CourseArrangeAndSchemeDao courseArrangeAndSchemeDao) {
       // this.courseArrangeAndSchemeRepository = courseArrangeAndSchemeRepository;
        this.courseArrangeAndSchemeDao = courseArrangeAndSchemeDao;
    }

    @Override
    public List<CourseArrangeAndScheme> getByClassId(Integer classId) {

        return courseArrangeAndSchemeDao.getByClassId(classId);
    }
}
