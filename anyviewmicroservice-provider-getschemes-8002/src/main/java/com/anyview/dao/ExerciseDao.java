package com.anyview.dao;

import com.anyview.entity.Exercise;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-10 22:03
 */
@Repository
public interface ExerciseDao {
    void saveExercise(@Param("eid") String eId, @Param("eContent") String eContent, @Param("lastTime") String commitTime);

    void updateCmpTimesDao(@Param("eid") String eid, @Param("cmpCount") int cmpcount, @Param("cmpRightCount") int cmprightcount, @Param("cmpErrorCount") int cmperrorcount);

    void updateRunResult(@Param("eid") String eid, @Param("runResult") int runresult, @Param("runErrCount") int runerrcount, @Param("firstPastTime") String runFinishTime);

    Exercise getExerciseContent(Long eid);

    Exercise getExercise(@Param("pid") Integer pid, @Param("vid") Integer vid, @Param("sid") Integer sid);

    Integer getStuExerciseCount(@Param("sid") Integer sid);

    void addExercise(@Param("pid") Integer pid, @Param("vid") Integer vid, @Param("eContent") String pContent, @Param("courseId") Integer courseId, @Param("sid") Integer sid, @Param("cid") Integer cid);

    /* add  */
    Exercise getExerciseByEid(int Eid);

    int saveExercise1(Exercise exercise);

    void saveRandom(Exercise exercise);
}
