package com.anyview.dao;

import com.anyview.entity.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {

    List<School> getAllUniversities();

    String getUnnameByUnid(int unid);
}
