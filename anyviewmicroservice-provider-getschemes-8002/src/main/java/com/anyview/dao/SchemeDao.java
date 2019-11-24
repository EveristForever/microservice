package com.anyview.dao;

import com.anyview.entity.Scheme;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:05
 */
@Repository
public interface SchemeDao {

    int selectCourseIdById(int vid);

    //返回关联作业表id和名字
    List<Map<String, Object>> getVidAndVName(int cid);

    List<Scheme> getByIds(List<Long> schemeIds);
}
