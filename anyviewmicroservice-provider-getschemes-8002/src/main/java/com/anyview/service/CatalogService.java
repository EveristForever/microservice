package com.anyview.service;

import com.anyview.dto.response.CatalogInfo;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:45
 */
public interface CatalogService {


    List<Map<Integer, String>> getVidAndVName(Integer cid);
    List<CatalogInfo> getCatalogs(Long schemeId, Long sid, Integer classId);

    CatalogInfo getByQuestionId(Integer questionId);
}
