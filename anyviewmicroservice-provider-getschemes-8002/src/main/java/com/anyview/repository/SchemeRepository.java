package com.anyview.repository;

import com.anyview.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jingshanccc
 * @create: 2019-02-02 15:07
 */
public interface SchemeRepository extends JpaRepository<Scheme, Long>, JpaSpecificationExecutor<Scheme> {

    List<Scheme> findByCourseIdInAndStatus(List<Long> courseIds, Integer status);

    List<Scheme> findByStatusAndTableNameLike(Integer status, String tableName);

    @Modifying
    @Query(value = "update Scheme set status=0 where id=?1")
    void disabledById(Long id);

    List<Scheme> findByIdInAndStatus(List<Long> schemeIds, Integer status);
}
