package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question_content")
public class QuestionContent implements Serializable {

    private static final long serialVersionUID = 4731904479092775728L;

    /** ID */
    @Id
    @Column(name = "id", updatable = false)
    private int id;

    /** 题目描述 */
    @Column(name = "question_description")
    private String questionDescription;

    /** 标准答案 */
    @Column(name = "standard_answer")
    private String standardAnswer;

    /** 学生答案 */
    @Column(name = "student_answer")
    private String studentAnswer;

    /** 主文件名称 */
    @Column(name = "mainfile_name")
    private String mainFileName;

    /** 主文件内容 */
    @Column(name = "mainfile_content")
    private String mainFileContent;

}
