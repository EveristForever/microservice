package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:45
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_course_schemetable")
public class CourseArrangeAndScheme implements Serializable {

    private static final long serialVersionUID = 5795936079081569585L;
    /** ID */
    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    /** 课程编排ID */
    @Column(name = "CTCID")
    private Long courseArrangeId;
    /** 班级ID */
    @Column(name="CID")
    private Integer classId; //classId未启用
    /** 作业表ID */
    @Column(name = "VID")
    private Long schemeId;

    /** 删除标志位 */
    @Column(name = "Enabled")
    private Integer status;

    // 作业表

    @Transient
    Scheme scheme;

}
