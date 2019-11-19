package com.anyview.dto;

import com.anyview.entity.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    public Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 用户姓名 */
    private String name;

    /** 用户邮箱 */
    private String email;

    /** 角色ID: -1-超级管理员 1-校级管理员 0-教师 3-学生 */
    private Long roleId;

    /** 学校ID */
    private Integer schoolId;

    /** 创建时间 */
    private Date createdDate;

    /** 创建者 */
    private String createdBy;

    /** 最后一次修改时间 */
    private Date lastUpdatedDate;

    /** 最后一次修改者 */
    private String lastUpdatedBy;

    School school;

}