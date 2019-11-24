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
 * @create: 2019-02-01 15:38
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "majortable")
public class Major implements Serializable {

    private static final long serialVersionUID = -8706967334356307734L;
    /** 专业ID */
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    /** 专业名称 */
    @Column(name = "major_name")
    private String majorName;

    /** 学院ID */
    @Column(name = "college_id")
    private Long collegeId;

    /** 更新者 */
    @Column(name = "major_updater")
    private String majorUpdater;

    /** 更新时间 */
    @Column(name = "major_update_time")
    private Date majorUpdateTime;

    /** 创建者 */
    @Column(name = "major_creater")
    private String majorCreater;

    /** 创建时间 */
    @Column(name = "major_create_time")
    private Date majorCreateTime;

    /** 是否删除 */
    @Column(name = "Enabled")
    private Integer status;

    @Transient
    private College college;

    @Transient
    private List<ClassEntity> majorClassEntityList;
}
