package com.anyview.service.impl;

import com.anyview.dao.SchoolDao;
import com.anyview.entity.School;
import com.anyview.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-25 17:47
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    //private final SchoolRepository schoolRepository;
    //
    //private final SchoolDao schoolDao;
    //@Autowired
    //public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolDao schoolDao) {
    //    this.schoolRepository = schoolRepository;
    //    this.schoolDao = schoolDao;
    //}

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> getSchools() {
        // 从缓存中获取学校
        String key = "Schools";
        ListOperations<String, List<School>> operations = redisTemplate.opsForList();
        List<School> schools;
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            schools = operations.leftPop(key);
            return schools;
        }
        schools = schoolDao.getAllUniversities();
        operations.leftPush(key, schools);
        redisTemplate.expire(key,5, TimeUnit.MINUTES); //缓存学校列表（5分钟）
        return schools;
    }
}
