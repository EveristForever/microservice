package com.anyview.service;

import com.anyview.entity.Scheme;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-18 22:28
 */
public interface SchemeService {

    List<Scheme> getSchemesByIds(List<Long> schemeIds);

    List<Scheme> getSchemesByCourseIds(List<Long> courseIds);

}
