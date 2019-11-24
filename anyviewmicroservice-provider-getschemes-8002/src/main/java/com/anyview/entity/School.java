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
 * @create: 2019-02-01 15:31
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UniversityTable")
public class School implements Serializable {

    private static final long serialVersionUID = 600921205729511206L;

    @Id
    @Column(name = "UnID", updatable = false)
    private Integer id;
    /** 学校名称 */
    @Column(name = "UnName")
    private String schoolName;

    /** 创建者 */
    @Column(name = "Creater")
    private String schoolCreater;

    /** 创建时间 */
    @Column(name = "CreateTime")
    private Date schoolCreateTime;

    /** 更新者 */
    @Column(name = "Updater")
    private String schoolUpdater;

    /** 更新时间 */
    @Column(name = "UpdateTime")
    private Date schoolUpdateTime;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    @Transient
    private String schoolNameSort;

    @Transient
    private List<College> schoolCollegeList;

}
