package com.anyview.dao;

import com.anyview.dto.response.CatalogInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 17:15
 */
@Repository
public interface CatalogDao {

    //虚拟作业表 获取学生做题详细信息(编译正确次数、通过时间等信息---exercise表)
    List<CatalogInfo> getCatalogs(@Param("vid") Long schemeId, @Param("sid") Long sid, @Param("cid") Integer cid);

    //通过class_teacher_coursetable cid找id(ctcid) 再通过classcourseschemetable找vid
    List<Map<Integer, String>> getVidAndVName(Integer cid);

    CatalogInfo getByQuestionId(Integer questionId);
}
