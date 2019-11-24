package com.anyview.service.impl;

import com.anyview.dao.SchemeContentDao;
import com.anyview.entity.SchemeContent;
import com.anyview.service.SchemeContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-18 23:20
 */
@Service
public class SchemeContentServiceImpl implements SchemeContentService {

   // private final SchemeContentRepository schemeContentRepository;
    private final SchemeContentDao schemeContentDao;

    @Autowired
    public SchemeContentServiceImpl(/*SchemeContentRepository schemeContentRepository,*/ SchemeContentDao schemeContentDao) {
       // this.schemeContentRepository = schemeContentRepository;
        this.schemeContentDao = schemeContentDao;
    }

    @Override
    public List<SchemeContent> getContentBySchemeId(Long schemeId) {
        return schemeContentDao.getContentBySchemeId(schemeId);
    }

    /*
     * @Description: 按章节题目名排序(升序)
     * @param schemeId 作业表id
     * @return: 作业表内容
     */
/*    @Override
    public List<SchemeContent> getSortedContentBySchemeId(Long schemeId) {

        Specification<SchemeContent> specification = new Specification<SchemeContent>() {
            @Override
            public Predicate toPredicate(Root<SchemeContent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> conditionList = new ArrayList<>();
                conditionList.add(criteriaBuilder.equal((root.get("status")), 1));
                conditionList.add(criteriaBuilder.and(criteriaBuilder.equal((root.get("schemeId")), schemeId)));

                Predicate[] pre = new Predicate[conditionList.size()];
                //conditionList.toArray(pre);
                query.where(conditionList.toArray(pre));
                query.orderBy(criteriaBuilder.asc(root.get("VChapName")), criteriaBuilder.asc(root.get("VPName")));
                return null;
            }
        };

        return schemeContentRepository.findAll(specification);
    }*/

    @Override
    public List<Map<Integer, String>> getSchemeContentList(Integer schemeId) {
        return schemeContentDao.getSchemeContentList(schemeId);
    }
}
