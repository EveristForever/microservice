package com.anyview.enums;

import lombok.Getter;

/**
 * @author jingshanccc
 * @description
 * @create: 2019-02-05 11:11
 */
@Getter
public enum RoleEnum {

    TEACHER(0L,"教师"),
    STUDENT(3L,"学生"),
    MANAGER(1L,"学校管理员"),
    SUPER_MANAGER(-1L,"超级管理员");

    private Long id;
    private String msg;

    RoleEnum(Long id, String msg){
        this.id = id;
        this.msg = msg;
    }
}
