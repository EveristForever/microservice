package com.anyview.entity;

import com.anyview.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:41
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachertable")
public class Teacher extends User implements Serializable {

    private static final long serialVersionUID = -1360926158000490492L;
    /** ID */
    @Id
    @Column(name = "TID", updatable = false)
    private Long id;

    /** 老师姓名 */
    @Column(name = "TName")
    private String name;

    /** 老师性别 */
    @Column(name = "TSex")
    private String sex;

    /** 老师所属学校 */
    @Column(name = "UnID")
    private Integer schoolId;

    /** 老师所属学院 */
    @Column(name = "CID")
    private Integer collegeId;

    /** 老师登录用户名 */
    @Column(name = "TNo")
    private String username;

    /** 老师登陆密码 */
    @Column(name = "TPsw")
    private String password;

    /** 老师删除标志位 */
    @Column(name = "Enabled")
    private int status;

    /** 老师邮箱 */
    @Column(name = "EMAIL")
    private String email;

    /** 是否锁定 */
    @Column(name = "IS_LOCKED")
    private Boolean isLocked;

    /** 锁定日期 */
    @Column(name = "LOCKED_DATE")
    private Date lockedDate;

    /** 连续登录失败次数 */
    @Column(name = "LOGIN_FAILURE_COUNT")
    private Integer loginFailureCount;

    /** 最后登录日期 */
    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /** 最后登录IP */
    @Column(name = "LAST_LOGIN_IP")
    private String lastLoginIp;

    /** 创建时间 */
    @Column(name = "CreateTime")
    private Date createdDate;

    /** 创建者 */
    @Column(name = "Creater")
    private String createdBy;

    /** 最后一次修改时间 */
    @Column(name = "UpdateTime")
    private Date lastUpdatedDate;

    /** 最后一次修改者 */
    @Column(name = "Updater")
    private String lastUpdatedBy;

    /** 角色ID: 0-超级管理员 1-校级管理员 2-教师 3-学生 */
    @Column(name = "RoleId")
    private Long roleId;

    @Transient
    private School school;
    // 老师所属学院
    @Transient
    private College college;
}
