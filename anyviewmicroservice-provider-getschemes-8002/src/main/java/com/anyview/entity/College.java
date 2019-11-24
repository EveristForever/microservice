package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:34
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CollegeTable")
public class College implements Serializable {

    private static final long serialVersionUID = -222624682316234848L;
    /** 学院ID */
    @Id
    @Column(name = "CeID", updatable = false)
    private Long id;

    /** 学院名称 */
    @Column(name = "CeName")
    private String collegeName;

    /** 学校ID */
    @Column(name = "UnID")
    private Integer schoolId;

    /** 更新者 */
    @Column(name = "Updater")
    private String collegeUpdater;

    /** 更新时间 */
    @Column(name = "UpdateTime")
    private Date collegeUpdateTime;

    /** 创建者 */
    @Column(name = "Creater")
    private String collegeCreater;

    /** 创建时间 */
    @Column(name = "CreateTime")
    private Date collegeCreateTime;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    @Transient
    private List<Major> collegeMajorList;

    @Transient
    private List<Course> collegeCourseList;

    @Transient
    private List<Teacher> collegeTeacherList;

    @Transient
    private School school;
}
