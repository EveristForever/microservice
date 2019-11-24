package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-02-17 15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question_headerfile")
public class QuestionHeaderFile {

    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @Column(name = "id", updatable = false)
    private int id;

    /** 头文件名称 */
    @Column(name = "header_file_name")
    private String headerFileName;

    /** 头文件内容 */
    @Column(name = "header_file_content")
    private String headerFileContent;

}

