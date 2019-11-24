package com.anyview.repository;

import com.anyview.entity.CourseArrangeAndScheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseArrangeAndSchemeRepository extends JpaRepository<CourseArrangeAndScheme, Long> {

    List<CourseArrangeAndScheme> findByCourseArrangeIdAndStatus(Long courseArrangeId, Integer status);

    List<CourseArrangeAndScheme> findBySchemeIdAndStatus(Long schemeId, Integer status);

    List<CourseArrangeAndScheme> findAllByCourseArrangeIdIn(List<Long> arrangeIds);

    List<CourseArrangeAndScheme> findByClassIdAndStatus(Integer classId, Integer status);

}
