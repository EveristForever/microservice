package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jingshanccc
 * @create: 2019-02-01 16:06
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schemecontenttable")
public class SchemeContent implements Serializable {

    private static final long serialVersionUID = -5985728971760235844L;
    /** 作业内容表id */
    @Id
    @Column(name = "ID", updatable = false)
    private Long ID;

    /** 作业表id */
    @Column(name = "VID")
    private Long schemeId;

    /** 题目id */
    @Column(name = "PID")
    private Long questionId;

    /** 题目名 */
    @Column(name = "VPName")
    private String VPName;

    /** 章节 */
    @Column(name = "VChapName")
    private String VChapName;

    /** 状态 */
    @Column(name = "Status")
    private int tStatus;

    /** 分值 */
    @Column(name = "Score")
    private float Score;

    /** 更新者 */
    @Column(name = "Updater")
    private String Updater;

    /** 开始时间 */
    @Column(name = "StartTime")
    private Date StartTime;

    /** 结束时间 */
    @Column(name = "FinishTime")
    private Date FinishTime;

    /** 更新时间 */
    @Column(name = "UpdateTime")
    private Date UpdateTime;

    /** 难度 */
    @Column(name = "difficulty")
    private double difficulty;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    @Transient
    private Scheme scheme;

    @Transient
    private Question question;

    @Transient
    private String difficultySort;

    @Transient
    private String VChapNameSort;

}
