package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "problemtable")
public class Question implements Serializable {

    private static final long serialVersionUID = -3315407843643487283L;
    /** ID */
    @Id
    @Column( name = "PID", updatable = false)
    private Long id;

    /** 题目名称 */
    @Column(name = "PName")
    private String questionName;

    /** 章节 */
    @Column(name = "chapter")
    private String chapter;

    /** 公开级别 */
    @Column(name = "public_level")
    private int publicLevel;

    /** 特定学校 */
    @Column(name = "specific_school")
    private String specificSchool;

    /** 难度 */
    @Column(name = "Degree")
    private float difficulty;

    /** 状态 */
    @Column(name = "state")
    private String state;

    /** 备注 */
    @Column(name = "PMemo")
    private String remark;

    /** 题库ID */
    @Column(name = "LID")
    private Long questionBankId;

    /** 题目内容 */
    @Column(name = "PContent")
    private String content;

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

    // 题库
    @Transient
    private QuestionBank questionBank;
}
