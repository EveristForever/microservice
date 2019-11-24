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
 * @description: 批改作业表
 * @author jingshanccc
 * @create: 2019-02-01 15:58
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercisetable")
public class Exercise implements Serializable {

    private static final long serialVersionUID = 6469087957424817845L;

    /** 作业答案ID */
    @Id
    @Column(name = "EID", updatable = false)
    private Long id;

    /** 学生ID */
    @Column(name = "SID")
    private Long studentId;

    /** 作业表ID */
    @Column(name = "VID")
    private Long schemeId;

    /** 题目ID */
    @Column(name = "PID")
    private Long questionId;

    /** 班级ID */
    @Column(name = "CID")
    private Integer classId;

    /** 课程ID */
    @Column(name = "CourseID")
    private Long courseId;

    /** 是否通过标志位 */
    @Column(name = "Kind")
    private Integer kind;

    /** 答案内容 */
    @Column(name = "EContent")
    private String eContent;

    /** 教师批注 */
    @Column(name = "EComment")
    private String eComment;

    /** 更新者 */
    @Column(name = "UpdatePerson")
    private String updatePerson;

    /** 完成题目所花时间 */
    @Column(name = "AccumTime")
    private Integer accumTime;

    /*完成时间*/
    @Column(name ="FirstPastTime")
    private Date firstPastTime;

    @Column(name = "LastTime")
    private Date lastTime;

    /** 更新日期 */
    @Column(name = "UpdateTime")
    private Date updateTime;

    /** 创建日期 */
    @Column(name = "CreateTime")
    private Date createTime;

    /** 删除标志位 */
    @Column(name = "Enabled")
    private Integer status;

    /** 综合分 */
    @Column(name = "Score")
    private float score;

    /** 卷面分 */
    @Column(name = "pageScore")
    private float pageScore;

    /** 态度分 */
    @Column(name = "attitudeScore")
    private float attitudeScore;

    /** 运行结果 */
    @Column(name = "RunResult")
    private int runResult;

    /** 运行错误次数 */
    @Column(name = "RunErrCount")
    private int runErrCount;

    /** 编译次数 */
    @Column(name = "CmpCount")
    private int cmpCount;

    /** 编译正确次数 */
    @Column(name = "CmpRightCount")
    private int cmpRightCount;

    /** 编译错误次数 */
    @Column(name = "CmpErrorCount")
    private int cmpErrorCount;

    /** 题目产生的random值*/
    @Column(name = "random")
    private String random;

    /** 错误数据的组次*/
    @Column(name = "errorOrder")
    private String errorOrder;

    // 通过率
    private float passPercent;
    // 学生
    private Student student;
    // 作业表
    private Scheme scheme;
    // 题目
    private Question question;
    // 班级
    private ClassEntity classEntitySystem;
    // 课程
    private Course course;

}
