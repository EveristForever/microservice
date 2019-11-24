package com.anyview.dao;

import com.anyview.entity.CourseArrangeAndScheme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-26 16:48
 */

public interface CourseArrangeAndSchemeDao {

    List<CourseArrangeAndScheme> getByClassId(@Param("cid") Integer classId);

}
