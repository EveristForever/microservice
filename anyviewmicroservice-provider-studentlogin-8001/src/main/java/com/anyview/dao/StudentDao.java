package com.anyview.dao;

import com.anyview.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface StudentDao {
    Student findStudentLoginState(Long sid);

    void updateStudentLoginState(Long sid);

    List<Student> getStudentInfo(@Param("sno") String userAccount, @Param("unid") int universityID);

    List<Student> getStudent(int sid);

    Student getStudentById(Long id);

    void setnewPassword(@Param("sid") int sid, @Param("spsw") String newSpsw);

    void setLoginSuccessStatus(@Param("sno") String sno, @Param("unid") int universityID, @Param("ip") String ip, @Param("port") int port, @Param("logtime") Timestamp timestamp);

}
