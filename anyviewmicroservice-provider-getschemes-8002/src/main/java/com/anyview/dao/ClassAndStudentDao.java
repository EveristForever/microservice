package com.anyview.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-19 18:23
 */
@Repository
public interface ClassAndStudentDao {

    Integer getClassIdByStudentId(@Param("sid") Integer studentId);
}
