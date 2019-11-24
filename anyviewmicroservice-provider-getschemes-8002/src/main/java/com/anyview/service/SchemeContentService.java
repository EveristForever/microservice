package com.anyview.service;

import com.anyview.entity.SchemeContent;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-18 23:19
 */
public interface SchemeContentService {

    List<SchemeContent> getContentBySchemeId(Long schemeId);

 //   List<SchemeContent> getSortedContentBySchemeId(Long schemeId);

    List<Map<Integer, String>> getSchemeContentList(Integer schemeId);
}
