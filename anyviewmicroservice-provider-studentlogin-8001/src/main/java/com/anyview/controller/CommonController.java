package com.anyview.controller;

import com.alibaba.fastjson.JSON;
import com.anyview.dto.SocketMsg;
import com.anyview.dto.User;
import com.anyview.dto.request.ChangePwdRequest;
import com.anyview.dto.response.ChangePwdResponse;
import com.anyview.entity.Student;
import com.anyview.enums.RoleEnum;
import com.anyview.enums.SocketMsgEnum;
import com.anyview.netty.ChannelIdRel;
import com.anyview.service.StudentService;
import com.anyview.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-04-19 16:38
 */
public class CommonController {

    private StudentService studentService = (StudentService) SpringUtil.getBean("studentServiceImpl");


    public SocketMsg loginAction(User user, String ip, int port, Channel curChannel){
        SocketMsg socketRes = null;
        String username = user.getUsername();
        if (ChannelIdRel.get(username)!=null){
            //已登录 先退出 再进行登录操作
            Channel oldChannel = ChannelIdRel.get(username);
            ChannelIdRel.removeByKey(username);
            SocketMsg<String> msg = new SocketMsg<>(SocketMsgEnum.LoginOuted.getType(), SocketMsgEnum.LoginOuted.getMessage());
            oldChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
        }
        //未登录/重新登录
        if (user.getRoleId().equals(RoleEnum.STUDENT.getId())) { //学生登录
            String sNo = user.getUsername();
            Integer schoolId = user.getSchoolId();
            List<Student> students = studentService.getStudentInfo(sNo, schoolId);
            if (students.size() > 0) {
                Student student = students.get(0);
                //账号存在，校验账户有效性、登录状态、密码
                if (student.getLoginStatus() == null) {
                    student.setLoginStatus(0);
                }
                if (student.getStatus() == 0) {
                    //账户无效(已停用)
                    socketRes = new SocketMsg<>(SocketMsgEnum.LoginFailed.getType(), "账号已停用");
                } else if (user.getPassword().equals("") && student.getPassword().equals("")) {
                    //账户有效，密码正确(都为空)
                    socketRes = new SocketMsg<Student>(SocketMsgEnum.LoginSuc.getType(), student);
                    //更新数据库:登录状态
                    studentService.setLoginStatusSuccess(sNo, schoolId, ip, port);
                    ChannelIdRel.put(user.getUsername(), curChannel);
                    ChannelIdRel.output();
                } else if (student.getPassword().equals(DigestUtils.md5Hex(String.valueOf(student.getId()) + user.getPassword()))) {
                    //账户有效 密码正确
                    socketRes = new SocketMsg<Student>(SocketMsgEnum.LoginSuc.getType(), student);
                    //更新数据库:登录状态
                    studentService.setLoginStatusSuccess(sNo, schoolId, ip, port);
                    ChannelIdRel.put(user.getUsername(), curChannel);
                    ChannelIdRel.output();
                } else {
                    //账户有效 密码错误
                    System.out.println("密码错误");
                    socketRes = new SocketMsg<String>(SocketMsgEnum.LoginFailed.getType(), "密码错误");
                }

            } else { //账号不存在
                System.out.println("账户无效");
                socketRes = new SocketMsg<String>(SocketMsgEnum.LoginFailed.getType(), "账号不存在");
            }
        }
        return socketRes;
    }

    public SocketMsg<String> logoutAction(User user){
        if (user.getRoleId().equals(RoleEnum.STUDENT.getId())){
            //学生登出
            studentService.updateLoginState(user.getId());
        }
        ChannelIdRel.removeByKey(user.getUsername());
        return new SocketMsg<>(SocketMsgEnum.LogoutRes.getType(),"用户已退出");
    }

    public SocketMsg<ChangePwdResponse> changePwdAction(ChangePwdRequest request){
        Long id = request.getId();
        String ID = String.valueOf(request.getId());
        String oldPsw = request.getOldPwd();
        String newPsw = request.getNewPwd();
        Long roleId = request.getRoleId();
        User u = new User();
        ChangePwdResponse<User> response = new ChangePwdResponse<>();
        if (roleId.equals(RoleEnum.STUDENT.getId())){ //学生修改密码
            u = studentService.getById(id);
            if (oldPsw.equals("") && u.getPassword().equals("")){
                studentService.updatePwdById(id, DigestUtils.md5Hex(ID+newPsw));
                response.setChangePswState(1);
                response.setMsg("密码修改成功！请使用新密码重新登录...");
            } else if (!u.getPassword().equals(DigestUtils.md5Hex(ID+oldPsw))){
                //旧密码错误
                response.setChangePswState(0);
                response.setMsg("修改失败！旧密码不正确！");
            } else {
                studentService.updatePwdById(id, DigestUtils.md5Hex(ID+newPsw));
                response.setChangePswState(1);
                response.setMsg("密码修改成功！请使用新密码重新登录...");
            }
        }
        return new SocketMsg<>(SocketMsgEnum.ChangePwdRes.getType(), response);
    }
}
