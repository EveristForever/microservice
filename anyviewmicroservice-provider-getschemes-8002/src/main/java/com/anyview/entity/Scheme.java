package com.anyview.entity;

import com.anyview.dto.response.CatalogInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description 作业表
 * @author jingshanccc
 * @create: 2019-02-01 15:44
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schemetable")
public class Scheme implements Serializable {

    private static final long serialVersionUID = -4868788980733817111L;
    /** 作业表ID */
    @Id
    @Column(name = "VID", updatable = false)
    private Long id;

    /** 作业表名 */
    @Column(name = "VName")
    private String tableName;

    /** 作业表类型 */
    @Column(name = "Kind")
    private int tableType;

    /** 课程id */
    @Column(name = "CourseID")
    private Long courseId;

    /** 级别 */
    @Column(name = "Visit")
    private int tableLevel;

    /** 状态 */
    @Column(name = "Status")
    private int tableStatus;

    /** 创建者 */
    @Column(name = "table_creater")
    private String tableCreater;

    /** 创建时间 */
    @Column(name = "CreateTime")
    private Date tableCreateTime;

    /** 更新者 */
    @Column(name = "table_updater")
    private String tableUpdater;

    /** 更新时间 */
    @Column(name = "UpdateTime")
    private Date tableUpdateTime;

    /** 总时间 */
    @Column(name = "totalTime")
    private int totalTime;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    /** 满分值 */
    @Column(name = "FullScore")
    private float fullScore;

    /** 总题数 */
    @Column(name = "TotalNum")
    private int totalNum;

    @Transient
    private String courseName;

    @Transient
    private String collegeName;

    @Transient
    private String schoolName;

    @Transient
    private Course course;

    @Transient
    private List<Long> courseIdList;

    @Transient
    private List<Long> tableIdList;

    //包含学生做题信息等内容
    @Transient
    private List<CatalogInfo> catalogs; //2019.4.19修改 方便前端数据的展现

    @Transient
    private String updaterSort;
}
