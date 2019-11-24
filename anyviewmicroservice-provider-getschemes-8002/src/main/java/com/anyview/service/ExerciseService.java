package com.anyview.service;

import com.anyview.entity.Exercise;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-19 17:51
 */
public interface ExerciseService {

    Integer getStuExerciseNum(Integer sid);

    Exercise getExercise(Integer questionId, Integer schemeId, Integer sid);

    Exercise getById(Long id);

    void addExercise(Integer questionId, Integer schemeId, String questionContent, Integer courseId, Integer sid, Integer classId);

    /*  add   */
    Exercise getExercise(int eid);

    int updateExercise(Exercise exercise1);

    void saveRandom(Exercise exercise);
}
