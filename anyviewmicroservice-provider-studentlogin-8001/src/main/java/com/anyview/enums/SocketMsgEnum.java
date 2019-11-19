package com.anyview.enums;

import lombok.Getter;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-31 23:02
 */
@Getter
public enum SocketMsgEnum {

    LoginReq(0,"登录请求"),


    SchemeReq(3,"获取作业表请求"),
    QuestionReq(4,"获取题目请求"),
    LogoutReq(5,"登出请求"),
    SchoolReq(6,"获取学校列表请求"),
    ChangePwdReq(7,"修改密码请求"),
    GetLastCacheReq(8,"恢复上次页面状态请求"),
    SetLastCacheReq(9,"保存页面状态请求"),

    LoginSuc(-1,"登录成功"),
    Logined(-11,"已登录"),
    LoginOuted(-13,"用户已在地方登录"),
    LoginFailed(-12,"登录失败"),


    SchemeRes(31,"作业表响应"),
    QuestionRes(41,"题目内容响应"),
    LogoutRes(51,"登出响应"),
    SchoolRes(61,"获取学校列表响应"),
    ChangePwdRes(71,"修改密码响应"),
    GetLastCacheRes(81,"恢复上次页面响应"),
    SetLastCacheRes(91,"保存页面状态响应"),



    CompileReq(10,"编译请求"),
    RunSingleReq(11,"单组运行请求"),
    RunGroupReq(161,"成组运行请求"),
    DebugReq(1,"调试请求"),
    StepReq(2,"下一步请求，step-into"),
    NextReq(121,"下一行请求, step-over"),
    ContinueReq(191,"继续调试请求"),
    QuitDebugReq(141,"终止调试請求"),
    ChangeBPReq(20, "增删断点请求"),
    GetFollowVariateReq(27,"继续展开变量请求"),
    SetWatchPointReq(29,"设置观察点请求"),
    InputReq(181,"输入请求"),
    SaveCodeReq(82,"保存学生答案请求"),
    HandleOrderReq(83, "禁用或启用数据组请求"),
    GetErrorOrderReq(85, "获取错误数据组请求"),
    EndRunningReq(87, "终止运行请求"),


    CompileRes(101,"编译响应"),
    RunSingleRes(111,"单组运行响应"),
    RunGroupRes(171,"成组运行响应"),
    DebugRes(131,"调试响应"),
    NextStepRes(21,"下一步调试响应，step/next/continue"),
    QuitDebugRes(151,"终止调试响应"),
    ChangeBPRes(22, "增删断点响应"),
    GetFollowVariateRes(28,"继续展开变量信息响应"),
    SetWatchPointRes(30,"设置观察点响应"),
    InputRes(25, "输入响应"),
    HandleOrderRes(84, "禁用或启用数据组响应"),
    GetErrorOrderRes(86, "获取错误数据组响应"),
    EndRunningRes(88, "终止运行响应"),


    ;


    private Integer type;
    private String message;

    SocketMsgEnum(Integer type, String message){
        this.type = type;
        this.message = message;
    }

}
