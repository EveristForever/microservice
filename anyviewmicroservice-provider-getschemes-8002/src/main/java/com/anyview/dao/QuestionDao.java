package com.anyview.dao;

import com.anyview.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:04
 */
@Repository
public interface QuestionDao {

    String getPContent(Integer pid);

    String selectPContentById(int pid);

    Question getById(Long id);
}
