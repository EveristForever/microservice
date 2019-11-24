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
 * @create: 2019-02-01 15:38
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classtable")
public class ClassEntity implements Serializable {

    private static final long serialVersionUID = 207006274717766874L;
    /** 班级ID */
    @Id
    @Column(name = "CID", updatable = false)
    private Integer id;

    /** 班级名称 */
    @Column(name = "Cname")
    private String className;

    /** 专业ID */
    @Column(name = "MID")
    private Integer majorId;

    /** 年届 */
    @Column(name = "StartYear")
    private Integer year;

    /** 更新者 */
    @Column(name = "CreatePerson")
    private String created_person;

    /** 更新日期 */
    @Column(name = "CreateTime")
    private Date created_date;

    /** 更新者 */
    @Column(name = "UpdatePerson")
    private String update_person;

    /** 更新日期 */
    @Column(name = "UpdateTime")
    private Date update_date;

    /** 删除标志位 */
    @Column(name = "Enabled")
    private Integer enabled;

    // 专业
    @Transient
    private Major major;

    // 课程编排
    @Transient
    private List<CourseArrange> classCourseArrangeList;

    // 学生与班级关联表
    @Transient
    private List<ClassAndStudent> classStudentList;
}
