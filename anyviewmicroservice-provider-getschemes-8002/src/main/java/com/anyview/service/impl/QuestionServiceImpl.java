package com.anyview.service.impl;

import com.anyview.dao.QuestionDao;
import com.anyview.entity.Question;
import com.anyview.repository.QuestionRepository;
import com.anyview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-19 13:14
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionDao questionDao) {
        this.questionRepository = questionRepository;
        this.questionDao = questionDao;
    }

    @Override
    public String getPContentById(Long questionId) {
        return questionDao.getPContent(questionId.intValue());
    }

    @Override
    public Question getById(Long id) {
        return questionDao.getById(id);
    }

    @Override
    public List<Question> getSortedByIds(List<Long> ids) {
        Specification<Question> specification = (Specification<Question>) (root, query, criteriaBuilder) -> {
            List<Predicate> conditionList = new ArrayList<>();
            conditionList.add(criteriaBuilder.equal((root.get("status")),1));
            conditionList.add(criteriaBuilder.and(root.get("id").in(ids)));
            //todo 可增加排序条件
            Predicate[] pre = new Predicate[conditionList.size()];
            query.where(conditionList.toArray(pre));
            query.orderBy(criteriaBuilder.asc(root.get("chapter")), criteriaBuilder.asc(root.get("questionName")));
            return null;
        };
        return questionRepository.findAll(specification);
    }

    @Override
    public List<Question> getByIds(List<Long> ids) {
        return questionRepository.findByIdInAndStatus(ids,1);
    }


}
