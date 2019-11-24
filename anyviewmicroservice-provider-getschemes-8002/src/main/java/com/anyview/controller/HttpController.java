package com.anyview.controller;

import com.alibaba.fastjson.JSON;
import com.anyview.dto.QuestionRes;
import com.anyview.dto.SocketMsg;
import com.anyview.dto.User;
import com.anyview.dto.response.CatalogInfo;
import com.anyview.entity.*;
//import com.anyview.enums.RoleEnum;
import com.anyview.enums.SocketMsgEnum;
//import com.anyview.netty.ChannelIdRel;
import com.anyview.service.*;
import com.anyview.utils.XMLUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-07-21 16:51
 */
@Controller
public class HttpController {

    private final SchemeService schemeService;
    private final ClassAndStudentService classAndStudentService;
    private final CourseArrangeAndSchemeService courseArrangeAndSchemeService;
    private final QuestionService questionService;
    private final SchemeContentService schemeContentService;
    private final ExerciseService exerciseService;
    private final CatalogService catalogService;
//    private final StudentService studentService;
//    private final SchoolService schoolService;

    public HttpController(SchemeService schemeService, ClassAndStudentService classAndStudentService, CourseArrangeAndSchemeService courseArrangeAndSchemeService,QuestionService questionService, SchemeContentService schemeContentService, ExerciseService exerciseService, CatalogService catalogService/*, StudentService studentService, SchoolService schoolService*/) {
        this.schemeService = schemeService;
        this.classAndStudentService = classAndStudentService;
        this.courseArrangeAndSchemeService = courseArrangeAndSchemeService;
        this.questionService = questionService;
        this.schemeContentService = schemeContentService;
        this.exerciseService = exerciseService;
        this.catalogService = catalogService;
//        this.studentService = studentService;
//        this.schoolService = schoolService;
    }

    @ResponseBody
    @GetMapping("/getSchemes")
    public String getSchemes(@RequestParam Integer id){
        Long studentId = id.longValue();

        Integer classId  = classAndStudentService.getClassIdByStudentId(studentId);
        List<CourseArrangeAndScheme> courseArrangeAndSchemes = courseArrangeAndSchemeService.getByClassId(classId);
        List<Long> schemeIds = courseArrangeAndSchemes.stream().map(CourseArrangeAndScheme::getSchemeId).collect(Collectors.toList());
        List<Scheme> schemes = schemeService.getSchemesByIds(schemeIds);

        //将每张作业表的题目及内容放入作业表中方便前端数据的展示
        for (Scheme scheme : schemes) {
            List<SchemeContent> schemeContents = schemeContentService.getContentBySchemeId(scheme.getId());

            if (exerciseService.getStuExerciseNum(id) != schemeContents.size()){
                //如果vid对应学生的作业题的数目和vid取出的作业表的目录的数量不一致，学生的作业表应添加这些题目
                for (SchemeContent schemeContent: schemeContents){

                    //如果exercise作业表没有找到
                    if (exerciseService.getExercise(schemeContent.getQuestionId().intValue(),schemeContent.getSchemeId().intValue(),studentId.intValue()) == null){
                        String questionContent = questionService.getPContentById(schemeContent.getQuestionId());
                        //插入
                        exerciseService.addExercise(schemeContent.getQuestionId().intValue(),schemeContent.getSchemeId().intValue(),questionContent,scheme.getCourseId().intValue(),id,classId);
                    }
                }
            }
            //取出作业表虚拟目录
            List<CatalogInfo> catalogInfoList = catalogService.getCatalogs(scheme.getId(),studentId,classId);
            scheme.setCatalogs(catalogInfoList);
        }
        SocketMsg<List<Scheme>> socketMsg = new SocketMsg<>(SocketMsgEnum.SchemeRes.getType(),schemes);
        return JSON.toJSONString(socketMsg);

    }

    @ResponseBody
    @GetMapping("/getExercise")
    public String getExercise(@RequestParam Long exerciseId){
        //未做过的题目 exercise中econtent字段的值为question中的content(原因：学生获取作业表时若exercise数目与题目数目不一致 添加exercise并将exercise中econtent设置为question的content)

        Exercise exercise = exerciseService.getById(exerciseId);
        Question question = questionService.getById(exercise.getQuestionId());
        QuestionRes questionRes = null;
        try {
            questionRes = XMLUtil.parseProblemXMLToString(question.getChapter()+"-"+question.getQuestionName(),question.getId().intValue(),question.getContent());
            if (exercise.getCmpCount()!=0){ //编译次数不为0 则econtent只有学生答案(可能有题目)
                //修改questionRes中的学生答案
                String studentAnswer = XMLUtil.exerciseContent(exercise.getEContent());
                QuestionContent questionContent = questionRes.getQuestionContent();
                questionContent.setStudentAnswer(studentAnswer);
                questionRes.setQuestionContent(questionContent);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        SocketMsg<QuestionRes> socketMsg = new SocketMsg<>(SocketMsgEnum.QuestionRes.getType(),questionRes);
        return JSON.toJSONString(socketMsg);
    }
/*
    @ResponseBody
    @PostMapping("/login")
    public String login(HttpServletRequest request){
        //获取request payload
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader();) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        User user = JSON.parseObject(sb.toString(),User.class);
        String ip = request.getRemoteAddr();
        int port = request.getRemotePort();
        String token = "token_"+DigestUtils.md5Hex(user.getUsername());
        SocketMsg socketRes = null;
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
                } else if ((user.getPassword().equals("") && student.getPassword().equals(""))||
                        student.getPassword().equals(DigestUtils.md5Hex(student.getId() + user.getPassword()))) {
                    //账户有效，密码正确(都为空)
                    student.setToken(token);
                    ChannelIdRel.set(token,user.getUsername());
                    socketRes = new SocketMsg<Student>(SocketMsgEnum.LoginSuc.getType(), student);
                    //更新数据库:登录状态
                    studentService.setLoginStatusSuccess(sNo, schoolId, ip, port);
                } else {
                    //账户有效 密码错误
                    socketRes = new SocketMsg<String>(SocketMsgEnum.LoginFailed.getType(), "密码错误");
                }

            } else { //账号不存在
                socketRes = new SocketMsg<String>(SocketMsgEnum.LoginFailed.getType(), "账号不存在");
            }
        }
        return JSON.toJSONString(socketRes);
    }

    @ResponseBody
    @GetMapping("/logout")
    public String logout(Long sid,Long role,String token){
        ChannelIdRel.remove(token);
        if (role.equals(RoleEnum.STUDENT.getId())){
            //学生登出
            studentService.updateLoginState(sid);
        }
        SocketMsg<String> socketMsg =  new SocketMsg<>(SocketMsgEnum.LogoutRes.getType(),"用户已退出");
        return JSON.toJSONString(socketMsg);
    }

    @ResponseBody
    @GetMapping("/getSchool")
    public String getSchool(HttpServletRequest request){
        List<School> schools = schoolService.getSchools();
        SocketMsg<List<School>> socketRes = new SocketMsg<>(SocketMsgEnum.SchoolRes.getType(),schools);
        return JSON.toJSONString(socketRes);
    }*/
}
