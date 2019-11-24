package com.anyview.service.impl;

import com.anyview.dao.CatalogDao;
import com.anyview.dto.response.CatalogInfo;
import com.anyview.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:56
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogDao catalogDao;

    @Autowired
    public CatalogServiceImpl(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    @Override
    public List<Map<Integer, String>> getVidAndVName(Integer cid) {
        return catalogDao.getVidAndVName(cid);
    }

    @Override
    public List<CatalogInfo> getCatalogs(Long schemeId, Long sid, Integer cid) {
        return catalogDao.getCatalogs(schemeId,sid,cid);
    }

    @Override
    public CatalogInfo getByQuestionId(Integer questionId) {
        return catalogDao.getByQuestionId(questionId);
    }
}
