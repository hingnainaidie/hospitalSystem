package com.cqu.hospitalsystem.cache;

import com.cqu.hospitalsystem.dao.CheckitemDao;
import com.cqu.hospitalsystem.dao.DoctorDao;
import com.cqu.hospitalsystem.dao.PatientDao;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class QueueCache {
    private QueueCache(){}
    @Resource
    private RegistrationDao registrationDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private PatientDao patientDao;
    public static Map<Long,Queue> queueMap=new HashMap<Long, Queue>();
//    static {
//        //初始化集合
//        System.out.println("我要开始初始化了");
//        List<Doctor> doctorList=doctorDao.queryAll();
//        System.out.println(doctorList.size());
//        for(Doctor doctor:doctorList){
//            Long doc_id=doctor.getDocId();
//            Queue<User> q=new LinkedList<User>();
//            queueMap.put(doc_id,q);
//            List<Registration> regList=new LinkedList<Registration>();
//            regList=registrationDao.queryAllByDoctor(doc_id,1);
//            for(Registration reg:regList){
//                enQueue(reg.getRegId());
//            }
//        }
//        for(Doctor doctor:doctorList){
//            Queue<User> q=queueMap.get(doctor.getDocId());
//            System.out.println("医生id为："+doctor.getDocId());
//            for (User u:q){
//                System.out.println("患者id："+u.getId()+" 患者名："+u.getName()+" 当前排队号："+u.getSep());
//            }
//        }
//    }
    //进队
    public void enQueue(Long reg_id){
        Registration registration=registrationDao.queryById(reg_id);
        Queue<User> q=new LinkedList<User>();
        q=queueMap.get(registration.getDocId());
        Patient patient=patientDao.queryById(registration.getPatientId());
        User u=new User(patient.getPatientId(),reg_id,patient.getPatientName());
        q.add(u);//队列尾增加一个人
        u.setSep(q.size());//定位新增加用户在队列中的id
    }
//
//    //队列中随机出队
//    public void deQueue(Long reg_id){
//        Registration registration=registrationDao.queryById(reg_id);
//        Queue<User> q=new LinkedList<User>();
//        q=queueMap.get(registration.getDocId());
//        Patient patient=patientDao.queryById(registration.getPatientId());
//        User u=new User(patient.getPatientId(),patient.getPatientName());
//        System.out.println(q.size());
//        q.remove(u);
//        System.out.println(q.size());
//        queueMap.put(registration.getDocId(),q);
//        updateSeq(reg_id);
//    }
//    //队列中对首元素出队
//    public void deQueue(Long doc_id){
//        Queue<User> q=new LinkedList<User>();
//        System.out.println(q.size());
//        q.poll();
//        System.out.println(q.size());
//        queueMap.put(doc_id,q);
//        updateSeq(doc_id);
//    }

//    //出队后更新队列中每个人的序列
//    public void updateSeq(Long doc_id){
//        Queue<User> q=new LinkedList<User>();
//        q=queueMap.get(doc_id);
//        int i=1;
//        for (User u:q){
//            u.setSep(i++);
//        }
//    }
    //打印队列的信息
    public void  printList(){
        List<Doctor> doctorList=doctorDao.queryAll();
        for(Doctor doctor:doctorList){
            Queue<User> q=queueMap.get(doctor.getDocId());
            System.out.println("医生id为："+doctor.getDocId());
            for (User u:q){
                System.out.println("挂号id："+u.getRegId()+" 患者id："+u.getPatientId()+" 患者名："+u.getName()+" 当前排队号："+u.getSep());
            }
        }
    }
    //返回排队列表给前端
    public static Map<Long,Queue> returnMap(){
        return queueMap;
    }

    @PostConstruct  //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    public void init(){
        List<Doctor> doctorList=doctorDao.queryAll();
        System.out.println("初始化数据");
        for(Doctor doctor:doctorList){
            Long doc_id=doctor.getDocId();
            Queue<User> q=new LinkedList<User>();
            queueMap.put(doc_id,q);
            //将排队中的加入初始队列，将已缴费的更改状态加入初始队列
            List<Registration> regList=new LinkedList<Registration>();
            regList=registrationDao.queryAllByDoctor(doc_id,2);
            for(Registration reg:regList){
                enQueue(reg.getRegId());
            }
            List<Registration> regList1=new LinkedList<Registration>();
            regList1=registrationDao.queryAllByDoctor(doc_id,1);
            for(Registration reg:regList1){
                enQueue(reg.getRegId());
                Registration tem=new Registration();
                tem.setRegId(reg.getRegId());
                tem.setState(2);
                registrationDao.update(tem);
            }
        }
        printList();
    }
}

class User{
    private Long patient_id;
    private Long reg_id;
    private String name;
    private int sep;//表示每个人排队的位置
    public User(Long patient_id,Long reg_id,String name) {
        this.patient_id = patient_id;
        this.reg_id=reg_id;
        this.name = name;
    }

    public Long getPatientId() {
        return patient_id;
    }
    public Long getRegId() {
        return reg_id;
    }
    public void setPatientId(Long patient_id) {
        this.patient_id = patient_id;
    }
    public void setRegId(Long reg_id) {
        this.reg_id = reg_id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSep() {
        return sep;
    }

    public void setSep(int sep) {
        this.sep = sep;
    }
}
