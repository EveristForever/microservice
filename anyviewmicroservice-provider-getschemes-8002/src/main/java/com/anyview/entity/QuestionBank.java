package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:51
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problemlibtable")
public class QuestionBank implements Serializable {

    private static final long serialVersionUID = 3512134391251163275L;

    @Id
    @Column(name = "LID", updatable = false)
    private Long id;

    /** 题库名称 */
    @Column(name = "LName")
    private String questionBankName;

    /** 课程名 */
    @Column(name = "course_name")
    private String courseName;

    /** 公开级别 */
    @Column(name = "public_level")
    private Integer publicLevel;

    /** 特定学校 */
    @Column(name = "specific_school")
    private String specificSchool;

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

    /** 删除标志位 */
    @Column(name = "Enabled")
    private Integer status;
}
