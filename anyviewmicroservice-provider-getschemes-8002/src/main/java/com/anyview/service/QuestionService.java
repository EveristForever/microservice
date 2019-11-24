package com.anyview.service;

import com.anyview.entity.Question;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-19 13:12
 */
public interface QuestionService {

    String getPContentById(Long questionId);

    Question getById(Long id);

    List<Question> getSortedByIds(List<Long> ids);
    List<Question> getByIds(List<Long> ids);
}
