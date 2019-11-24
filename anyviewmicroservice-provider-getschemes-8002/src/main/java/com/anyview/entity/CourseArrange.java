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
 * @create: 2019-02-01 15:40
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class_teacher_coursetable")
public class CourseArrange implements Serializable {

    private static final long serialVersionUID = -3076461891910705070L;
    /** ID */
    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    /** 班级ID */
    @Column(name = "CID")
    private Integer classId;

    /** 课程ID */
    @Column(name = "CourseID")
    private Long courseId;

    /** 老师ID */
    @Column(name = "TID")
    private Integer teacherId;

    /** 创建者 */
    @Column(name = "CreatePerson")
    private String createdPerson;

    /** 创建日期 */
    @Column(name = "CreateTime")
    private Date createdDate;

    /** 更新者 */
    @Column(name = "UpdatePerson")
    private String updatePerson;

    /** 更新日期 */
    @Column(name = "UpdateTime")
    private Date updateDate;

    /** 删除标志 */
    @Column(name = "Enabled")
    private Integer status;

    // 班级
    @Transient
    private ClassEntity classEntitySystem;
    // 学生与班级关联表
    @Transient
    private List<ClassAndStudent> classAndStudentList;
    // 课程
    @Transient
    private Course course;
    // 老师
    @Transient
    private Teacher teacher;
    // 课程编排与作业表关联表
    @Transient
    private List<CourseArrangeAndScheme> courseArrangeAndScheme;

}
