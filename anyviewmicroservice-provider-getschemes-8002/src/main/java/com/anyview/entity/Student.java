package com.anyview.entity;

import com.anyview.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author jingshanccc
 * @create: 2019-02-01 15:01
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studenttable")
public class Student extends User implements Serializable {

    private static final long serialVersionUID = -4456817152842583701L;

    /** id */
    @Id
    @Column(name = "SID", updatable = false)
    private Long id;

    /** 学生性别 */
    @Column(name = "Ssex")
    private String sex;

    /** 学生状态 */
    @Column(name = "State")
    private String state;

    /** 学生登录用户名 */
    @Column(name = "Sno")
    private String username;

    /** 删除标志位 */
    @Column(name = "Enabled")
    private Integer status;

    /** 学生登陆密码 */
    @Column(name = "SPsw")
    private String password;

    /** 学生姓名 */
    @Column(name = "SName")
    private String name;

    /** 学生所属学校 */
    @Column(name = "UnID")
    private Integer schoolId;

    /** 学生邮箱 */
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

    /** 累积做题时间 */
    @Column(name = "SaccumTime")
    private Integer saccumTime;

    @Column(name="LogTime")
    private Timestamp logTime;
    /** IP地址 */
    @Column(name = "LogIP")
    private String logIP;

    /** 端口号 */
    @Column(name = "LogPort")
    private Integer logPort;

    /** 登录状态 */
    @Column(name = "LoginStatus")
    private Integer loginStatus;

    // 学生与班级关联表
    @Transient
    private List<ClassAndStudent> classList;

    @Transient
    private List<Integer> studentIdList;

    // 学校
    @Transient
    private School school;
}
