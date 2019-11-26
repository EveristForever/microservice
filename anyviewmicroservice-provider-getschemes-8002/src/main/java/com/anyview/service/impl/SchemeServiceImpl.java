package com.anyview.service.impl;

import com.anyview.dao.SchemeDao;
import com.anyview.entity.Scheme;
import com.anyview.repository.SchemeRepository;
import com.anyview.service.SchemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-18 22:28
 */
@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

    private final SchemeRepository schemeRepository;
    private final SchemeDao schemeDao;

    @Autowired
    public SchemeServiceImpl(SchemeRepository schemeRepository, SchemeDao schemeDao) {
        this.schemeRepository = schemeRepository;
        this.schemeDao = schemeDao;
    }

    @Override
    public List<Scheme> getSchemesByIds(List<Long> schemeIds) {

        return schemeDao.getByIds(schemeIds);
    }

   @Override
    public List<Scheme> getSchemesByCourseIds(List<Long> courseIds) {
        return schemeRepository.findByCourseIdInAndStatus(courseIds,1);

    }
}
