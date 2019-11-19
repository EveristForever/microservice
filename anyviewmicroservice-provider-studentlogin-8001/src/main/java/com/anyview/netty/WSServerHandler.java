package com.anyview.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyview.controller.CommonController;
import com.anyview.dto.SocketMsg;
import com.anyview.dto.User;
import com.anyview.dto.request.ChangePwdRequest;
import com.anyview.dto.response.ChangePwdResponse;
import com.anyview.entity.School;
import com.anyview.enums.SocketMsgEnum;
import com.anyview.service.SchoolService;
/*import com.anyview.utils.FileDelete;*/
import com.anyview.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.File;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jingshanccc
 * @description: textWebSocketFrame是专门用来传输webSocket文本类的消息的frame载体
 * @create: 2019-03-24 15:17
 */
public class WSServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //存放所有连接到服务的的客户端channel
    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //创建路径
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String date = sdf.format(new Date());

    /*private CodeController codeController = new CodeController();*/

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel curChannel = ctx.channel();
        InetSocketAddress remoteAddress = (InetSocketAddress) curChannel.remoteAddress();
        String ip = remoteAddress.getAddress().getHostAddress();
        int port = remoteAddress.getPort();

        SocketMsg socketMsg = JSON.parseObject(msg.text(), SocketMsg.class);

        System.out.println(SocketMsgEnum.InputReq.getType() + "---" + socketMsg.getType());

        //登录
        if (socketMsg.getType().equals(SocketMsgEnum.LoginReq.getType())) {
            User user = JSON.toJavaObject((JSONObject) socketMsg.getContent(), User.class);
            CommonController commonController = new CommonController();
            SocketMsg socketRes = commonController.loginAction(user, ip, port, curChannel);
            curChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.LogoutReq.getType())) { //登出
            User user = JSON.toJavaObject((JSONObject) socketMsg.getContent(), User.class);
            CommonController commonController = new CommonController();
            SocketMsg<String> socketRes = commonController.logoutAction(user);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        }
        /*else if (socketMsg.getType().equals(SocketMsgEnum.SchemeReq.getType())) { //获取用户作业表
            Integer id = (Integer) socketMsg.getContent();
            StudentController studentController = new StudentController();
            SocketMsg<List<Scheme>> socketRes = studentController.getSchemeAction(id);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.QuestionReq.getType())) { //获取题目内容
            Integer id = (Integer) socketMsg.getContent();
            StudentController studentController = new StudentController();
            SocketMsg<QuestionRes> socketRes = studentController.getExerciseAction(id.longValue());
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } */
        else if (socketMsg.getType().equals(SocketMsgEnum.SchoolReq.getType())) { //获取学校列表
            SchoolService schoolService = (SchoolService) SpringUtil.getBean("schoolServiceImpl");
            List<School> schools = schoolService.getSchools();
            SocketMsg<List<School>> socketRes = new SocketMsg<>(SocketMsgEnum.SchoolRes.getType(), schools);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.ChangePwdReq.getType())) { //修改密码
            ChangePwdRequest request = JSON.toJavaObject((JSONObject) socketMsg.getContent(), ChangePwdRequest.class);
            CommonController commonController = new CommonController();
            SocketMsg<ChangePwdResponse> socketRes = commonController.changePwdAction(request);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } /*else if (socketMsg.getType().equals(SocketMsgEnum.DebugReq.getType())) { //调试
            DebugFormat debugFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), DebugFormat.class);
            SocketMsg socketRes = codeController.startDebug(debugFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.StepReq.getType())) { //下一行，跳入自定义函数
            NextStepFormat nextStepFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), NextStepFormat.class);
            SocketMsg socketRes = codeController.nextStep(nextStepFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.NextReq.getType())) { //下一行
            NextStepFormat nextStepFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), NextStepFormat.class);
            SocketMsg socketRes = codeController.nextStep(nextStepFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.QuitDebugReq.getType())) { //终止调试
            SocketMsg socketRes = codeController.quitDebug();
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.CompileReq.getType())) { //编译
            CompileFormat compileFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), CompileFormat.class);
            SocketMsg socketRes = codeController.compileCode(compileFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.RunSingleReq.getType())) { //单组运行
            RunFormat runFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), RunFormat.class);
            SocketMsg socketRes = codeController.runSingle(runFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.RunGroupReq.getType())) {//成组运行
            RunFormat runFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), RunFormat.class);
            SocketMsg socketRes = codeController.runGroup(runFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.InputReq.getType())) {
            //TODO

        } else if (socketMsg.getType().equals(SocketMsgEnum.ContinueReq.getType())) {   //继续调试
            NextStepFormat nextStepFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), NextStepFormat.class);
            SocketMsg socketRes = codeController.nextStep(nextStepFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.ChangeBPReq.getType())) {//增删断点
            ChangeBpOrWpFormat changeBpOrWpFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), ChangeBpOrWpFormat.class);
            SocketMsg socketRes = codeController.changeBp(changeBpOrWpFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.GetFollowVariateReq.getType())) {//继续展开变量信息
            String name = (String) socketMsg.getContent();
            SocketMsg socketRes = codeController.getFollowVariate(name);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.SetWatchPointReq.getType())) {//设置观察点
            ChangeBpOrWpFormat changeBporWpFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), ChangeBpOrWpFormat.class);
            SocketMsg socketRes = codeController.setWatchPoint(changeBporWpFormat);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.SaveCodeReq.getType())) {//保存学生答案
            SaveCodeFormat saveCodeFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), SaveCodeFormat.class);
            SocketMsg socketRes = codeController.saveStudentCode(saveCodeFormat.getEID(), saveCodeFormat.getStudentCode());
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.GetErrorOrderReq.getType())) {//获取错误数据集
            Integer eID = JSON.toJavaObject((JSONObject) socketMsg.getContent(), GetErrorOrderFormat.class).getEID();
            SocketMsg socketRes = codeController.getErrorOrder(eID);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        } else if (socketMsg.getType().equals(SocketMsgEnum.HandleOrderReq.getType())) {//启用或禁用错误数据组
            HandleErrorOrderFormat handleErrorOrderFormat = JSON.toJavaObject((JSONObject) socketMsg.getContent(), HandleErrorOrderFormat.class);
            SocketMsg socketRes = codeController.handleErrorOrder(handleErrorOrderFormat.getOrder(),
                    handleErrorOrderFormat.getAction(), handleErrorOrderFormat.getEID());
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));

        }else if (socketMsg.getType().equals(SocketMsgEnum.EndRunningReq.getType())) {//终止运行
            SocketMsg socketRes = codeController.endRunning();
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(socketRes)));
        }*/
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        clients.add(ctx.channel());
        System.out.println("客户端的channelId为：" + ctx.channel().id().asShortText());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String date1 = sdf.format(new Date());
        date = date1;
    }

/*
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {

        Channel curChannel = ctx.channel();
        //默认会执行remove操作 即从clients移除当前客户端的channel
        clients.remove(curChannel);
        //将已登录用户移除
        ChannelIdRel.removeByValue(curChannel);
        System.out.println(curChannel.id().asShortText() + "断开链接");
        //删除目录
        String path = "/var/my_anyview1/linux_any/" + WSServerHandler.date; //文件夹形式存储一道题目的所有文件
        File filePath = new File(path);
        if (filePath.exists()) {
            FileDelete.removeDir(filePath);
        }
    }
*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel channel = ctx.channel();
        cause.printStackTrace();
        channel.close();
        clients.remove(channel);
        ChannelIdRel.removeByValue(channel);
    }

}
