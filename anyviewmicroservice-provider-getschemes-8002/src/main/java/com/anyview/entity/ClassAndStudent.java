package com.anyview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:36
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_studenttable")
public class ClassAndStudent implements Serializable {

    private static final long serialVersionUID = -956779831817255459L;
    /** ID */
    @Id
    @Column( name = "ID", updatable = false)
    private long id;

    /** 学生班级ID */
    @Column(name = "CID")
    private Integer studentClassId;

    /** 学生ID */
    @Column(name = "SID")
    private Integer studentId;

    /** 删除标志位 */
    @Column(name = "Status")
    private Integer status;

    // 班级
    @Transient
    //此注解是标识非mapping属性
    private ClassEntity classEntitySystem;
    // 学生
    @Transient
    private Student student;

}
