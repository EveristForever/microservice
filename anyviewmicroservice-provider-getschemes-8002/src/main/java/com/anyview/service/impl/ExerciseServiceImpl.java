package com.anyview.service.impl;

import com.anyview.dao.ExerciseDao;
import com.anyview.entity.Exercise;
import com.anyview.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-19 17:52
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseDao exerciseDao;

    @Autowired
    public ExerciseServiceImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Integer getStuExerciseNum(Integer sid) {
        return exerciseDao.getStuExerciseCount(sid);
    }

    @Override
    public Exercise getExercise(Integer questionId, Integer schemeId, Integer sid) {
        return exerciseDao.getExercise(questionId,schemeId,sid);
    }

    @Override
    public Exercise getById(Long id) {
        return exerciseDao.getExerciseContent(id);
    }

    @Override
    public void addExercise(Integer questionId, Integer schemeId, String questionContent, Integer courseId, Integer sid, Integer classId) {
        exerciseDao.addExercise(questionId,schemeId,questionContent,courseId,sid,classId);
    }

    /*  20190908 add   */

    @Override
    public int updateExercise(Exercise exercise1){
        return exerciseDao.saveExercise1(exercise1);
    }

    public Exercise getExercise(int eid) {
        return exerciseDao.getExerciseByEid(eid);
    }

    @Override
    public void saveRandom(Exercise exercise){
         exerciseDao.saveRandom(exercise);
    }
}
