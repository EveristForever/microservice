package com.anyview.dao;

import com.anyview.dto.response.CatalogInfo;
import com.anyview.entity.SchemeContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:05
 */
@Repository
public interface SchemeContentDao {
    List<Map<String, Object>> getSchemeContentTable(@Param("pid") Integer pid, @Param("vid") Integer vid);

    List<Map<Integer, String>> getSchemeContentList(Integer vid);

    List<CatalogInfo> getCatalogContent(Integer vid);

    List<SchemeContent> getContentBySchemeId(@Param("vid") Long schemeId);
}
