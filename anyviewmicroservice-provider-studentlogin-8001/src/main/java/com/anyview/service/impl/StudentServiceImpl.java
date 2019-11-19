package com.anyview.service.impl;

import com.anyview.dao.StudentDao;
//import com.anyview.entity.Exercise;
import com.anyview.entity.Student;
//import com.anyview.repository.ClassAndStudentRepository;
//import com.anyview.repository.ExerciseRepository;
//import com.anyview.repository.StudentRepository;
import com.anyview.service.StudentService;
//import com.anyview.utils.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-02-08 11:36
 */
@Service
public class StudentServiceImpl implements StudentService {

    //private final StudentRepository studentRepository;
    //private final SystemUserConfig userConfig;
    //private final MailService mailService;
    //private final ExerciseRepository exerciseRepository;
    //private final ClassAndStudentRepository classAndStudentRepository;


    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(/*StudentRepository studentRepository, ExerciseRepository exerciseRepository, ClassAndStudentRepository classAndStudentRepository, */StudentDao studentDao) {
/*        this.studentRepository = studentRepository;
        //this.userConfig = userConfig;
        //this.mailService = mailService;
        this.exerciseRepository = exerciseRepository;
        this.classAndStudentRepository = classAndStudentRepository;*/
        this.studentDao = studentDao;
    }

    //学生端接口
    @Override
    public Student getById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void updatePwdById(Long id, String password) {
        studentDao.setnewPassword(id.intValue(),password);
    }

    @Override
    public List<Student> getStudentInfo(String sNo, Integer schoolId) {
        return studentDao.getStudentInfo(sNo,schoolId);
    }

    @Override
    public void setLoginStatusSuccess(String sNo, Integer schoolId, String ip, int port) {
        studentDao.setLoginSuccessStatus(sNo,schoolId,ip,port,new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void updateLoginState(Long studentId) {
        studentDao.updateStudentLoginState(studentId);
    }


/*    //以下为教师端接口方法
    @Override
    public List<Student> findTotal() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByEntity(Student studentNew) {
        //Specification<Student> studentSpecification = (Specification<Student>) (root, query, criteriaBuilder) -> {
        //    List<Predicate> list = new ArrayList<>();
        //    list.add(criteriaBuilder.equal(root.get("status"),1));
        //    if (!StringUtils.isEmpty(studentNew.getUsername())){
        //        list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userName"),studentNew.getUsername())));
        //    }
        //    if (studentNew.getRoleId()!=null){
        //        list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("roleId"),studentNew.getRoleId())));
        //    }
        //    if (!StringUtils.isEmpty(studentNew.getEmail())){
        //        list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"),studentNew.getEmail())));
        //    }
        //    if (studentNew.getSchoolId()!=null){
        //        list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("schoolId"),studentNew.getSchoolId())));
        //    }
        //    Predicate[] predicates = new Predicate[list.size()];
        //    query.where(list.toArray(predicates));
        //
        //    return null;
        //};
        Specification<Student> studentSpecification = SpecificationUtil.entitySpecification(studentNew);
        Optional<Student> optional = studentRepository.findOne(studentSpecification);
        return optional.orElse(null);
    }

    @Override
    public List<Student> findByAttribute(Student student) {

        student.setStatus(1);
        return studentRepository.findAll(Example.of(student));
    }

    @Override
    public Student findByUserNameAndSchoolId(Student student) {
        Specification<Student> studentSpecification = (Specification<Student>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("status"),1));
            if (!StringUtils.isEmpty(student.getUsername())){
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"),student.getUsername())));
            }
            if (student.getSchoolId()!=null){
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("schoolId"),student.getSchoolId())));
            }
            Predicate[] predicates = new Predicate[list.size()];
            query.where(list.toArray(predicates));
            return null;
        };
        Optional<Student> optional = studentRepository.findOne(studentSpecification);
        return optional.orElse(null);
    }

    @Override
    public List<Student> findEntityList(Student student) {
        Specification<Student> studentSpecification = (Specification<Student>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("status"),1));
            if (!StringUtils.isEmpty(student.getUsername())){
                list.add(criteriaBuilder.and(criteriaBuilder.like(root.get("userName"),"%"+student.getUsername()+"%")));
            }
            if (!StringUtils.isEmpty(student.getName())){
                list.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%"+student.getName()+"%")));
            }
            if (student.getStudentIdList().size()>0){
                list.add(criteriaBuilder.and(root.get("id").in(student.getStudentIdList())));
            }
            Predicate[] predicates = new Predicate[list.size()];

            query.where(list.toArray(predicates));
            return null;
        };
        return studentRepository.findAll(studentSpecification);
    }

    @Override
    public Student findByUserName(String name) {

        return studentRepository.findByUsernameAndStatus(name,1);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    ///*
    // * @Description: 重置随机密码
    // * @param student
    // * @return: void
    // */
    //@Override
    //public void updatePassByUserName(Student student) throws MessagingException, IOException {
    //    Map<String,String> map = GeneratePwdUtil.generateRandom(student);
    //    student.setPassword(map.get("password"));
    //    String content = student.getUsername() + userConfig.getResetPasswordStartContent() + map.get("code") + userConfig.getLoginAddressContent();
    //    studentRepository.save(student);
    //    mailService.send(student.getEmail(), userConfig.getNewUserTitle(), content);
    //
    //}
    //
    ///*
    // * @Description: 新增用户 密码为随机密码(有邮箱) 初始密码(无邮箱)
    // * @param student
    // * @return: void
    // */
    //@Override
    //public void saveOne(Student student) throws MessagingException, IOException {
    //    //保存到数据库以获得id
    //    studentRepository.save(student);
    //    Student s = findByEntity(student);
    //    student.setId(s.getId());
    //    //有邮箱
    //    if (!StringUtils.isEmpty(student.getEmail())){
    //        Map<String,String> map = GeneratePwdUtil.generateRandom(student);
    //        student.setPassword(map.get("password"));
    //        studentRepository.save(student);
    //        String content = student.getUsername() + userConfig.getNewUserStartContent() + student.getUsername() + userConfig.getNewUserMinContent() + map.get("code")
    //                + userConfig.getLoginAddressContent();
    //        mailService.send(student.getEmail(),userConfig.getNewUserTitle(),content);
    //    }else { //无邮箱
    //        student.setPassword(GeneratePwdUtil.generateInit(student));
    //        studentRepository.save(student);
    //    }
    //}
    //
    //@Override
    //public void updateInitPass(Student student) throws MessagingException, IOException {
    //    student.setPassword(GeneratePwdUtil.generateInit(student));
    //    studentRepository.save(student);
    //    if (!StringUtils.isEmpty(student.getEmail())){
    //        String content = student.getUsername() + userConfig.getInitTitlePasswordContent() + userConfig.getInitPassword() + userConfig.getLoginAddressContent();
    //        mailService.send(student.getEmail(),userConfig.getNewUserTitle(),content);
    //    }
    //}


/*    @Override
    public void deleteOne(List<String> recordStudent) throws Exception {
        List<Long> ids = recordStudent.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Exercise> exercises = exerciseRepository.findAllByStudentIdInAndStatus(ids,1);
        if (exercises.size()>0){
            throw new RuntimeException("请先删除该学生的所有批改作业！");
        }
        //删除学生
        studentRepository.disabledByIds(ids);
        //删除学生与班级关系
        classAndStudentRepository.disabledByStudentIds(ids);
    }*/


}
