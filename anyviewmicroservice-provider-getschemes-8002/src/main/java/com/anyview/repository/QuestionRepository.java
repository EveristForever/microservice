package com.anyview.repository;

import com.anyview.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    Question findByQuestionBankIdAndStatus(Long questionBankId, Integer status);

    @Query(value = "select id,questionName ,chapter ,publicLevel ,specificSchool ,difficulty ,state ,remark,questionBankId,content, createdPerson,createdDate,updatePerson,updateDate,status from Question where status = 1 AND questionName like %?1%")
    List<Question> findByFilterQuestionName(String questionName);

    List<Question> findByStatusAndQuestionBankIdAndQuestionNameLike(Integer status, Long questionBankId, String questionName);

    Question findByStatusAndId(Integer status, Long id);

    List<Question> findByIdInAndStatus(List<Long> ids, Integer status);

    List<Question> findAllByQuestionBankIdAndStatus(List<Long> questionBankIds, Integer status);

    @Modifying
    @Query(value = "update Question set status=0 where id in (?1)")
    void disabledAllByIds(List<Long> ids);

}
