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
 * @create: 2019-02-01 15:43
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CourseTable")
public class Course implements Serializable {

    private static final long serialVersionUID = 6823189469785200002L;
    /** 课程ID */
    @Id
    @Column(name = "CourseID", updatable = false)
    private Long id;

    /** 课程名称 */
    @Column(name = "CourseName")
    private String courseName;

    /** 学院ID */
    @Column(name = "CeID")
    private Long collegeId;

    /** 更新者 */
    @Column(name = "Updater")
    private String courseUpdater;

    /** 更新时间 */
    @Column(name = "UpdateTime")
    private Date courseUpdateTime;

    /** 创建者 */
    @Column(name = "Creater")
    private String courseCreater;

    /** 创建时间 */
    @Column(name = "CreateTime")
    private Date courseCreateTime;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    @Transient
    private College college;

    @Transient
    private List<CourseArrange> courseCourseArrangeList;

    @Transient
    private List<Scheme> schemeList;
}
