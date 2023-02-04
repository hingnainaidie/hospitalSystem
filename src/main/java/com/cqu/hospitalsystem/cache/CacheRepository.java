package com.cqu.hospitalsystem.cache;

import com.cqu.hospitalsystem.dao.DoctorDao;
import com.cqu.hospitalsystem.dao.PatientDao;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.Vo.RegSepout;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class CacheRepository {
    @Resource
    private RegistrationDao registrationDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private DoctorDao doctorDao;
    //通过patient_id获取排队号
    public int getQueueNum(Long patient_id){
        List<Registration> registrations=registrationDao.queryByPatient(patient_id,2);
        if(registrations.size()==0){
            return 0;
        }
        Registration registration=registrations.get(0);
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> queue=queueMap.get(registration.getDocId());
        for(User u:queue){
            if(u.getPatientId()==registration.getPatientId()){
                return u.getSep();
            }
        }
        return 0;
    }
    //通过patient_id获取reg_id
    public Long getSeqReg_id(Long patient_id){
        List<Registration> registrations=registrationDao.queryByPatient(patient_id,2);
        if(registrations.size()==0){
            return 0L;
        }
        Registration registration=registrations.get(0);
        return registration.getRegId();
    }
    //通过医生id获取数据
    public List<RegSepout> getQueue(Long doc_id){
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> queue=queueMap.get(doc_id);
        List<RegSepout> regSepouts=new LinkedList<RegSepout>();
        for(User u:queue){
            RegSepout regSepout=new RegSepout();
            Patient patient=patientDao.queryById(u.getPatientId());
            regSepout.setPatient(patient);
            regSepout.setRegId(u.getRegId());
            regSepouts.add(regSepout);
        }
        return regSepouts;
    }

    //新增reg数据排队在末端
    public List<RegSepout> push(Long reg_id){
        Registration reg=registrationDao.queryById(reg_id);
        Patient patient=patientDao.queryById(reg.getPatientId());
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        User user=new User(patient.getPatientId(),reg_id,patient.getPatientName());
        Queue<User> q=queueMap.get(reg.getDocId());
        int flag=0;
        for(User u:q){
            if(u.getPatientId()==patient.getPatientId()){
                flag=1;
            }
        }
        if(flag==0){
            q.add(user);//队列尾增加一个人
            user.setSep(q.size());//定位新增加用户在队列中的id
        }
        else{
            System.out.println("该患者已经在队列里面了");
        }
        //更新状态为排队中
        Registration registration=new Registration();
        registration.setRegId(reg_id);
        registration.setState(2);
        registrationDao.update(registration);
        //返回数据
        List<RegSepout> regSepouts=new LinkedList<RegSepout>();
        for(User u:q){
            RegSepout regSepout=new RegSepout();
            Patient patient1=patientDao.queryById(u.getPatientId());
            regSepout.setPatient(patient1);
            regSepout.setRegId(u.getRegId());
            regSepouts.add(regSepout);
        }
        return regSepouts;
    }

    //入队3-10位置
    public List<RegSepout> pushInsert(Long reg_id){
        Registration registration=registrationDao.queryById(reg_id);
        Patient patient=patientDao.queryById(registration.getPatientId());
        User user=new User(patient.getPatientId(),reg_id,patient.getPatientName());
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> q=queueMap.get(registration.getDocId());
        //生成随机解挂排序插入数
        Random random=new Random();
        int a=random.nextInt(7)+3;
        //如果生成排序数小于当前排队人数，直接插入到末尾
        if(a>=q.size()){
            push(reg_id);
        }
        //否则插入到随机数的位置
        else{
            List<User> userList=new LinkedList<User>();
            while (q.peek()!=null){
                userList.add(q.poll());
            }
            userList.add(a,user);
            for(int i=0;i<userList.size();i++){
                q.add(userList.get(i));
            }
        }
        updateSeq(registration.getDocId());
        //更新状态为排队中
        Registration reg=new Registration();
        reg.setRegId(reg_id);
        reg.setState(2);
        registrationDao.update(reg);
        //返回数据
        List<RegSepout> regSepouts=new LinkedList<RegSepout>();
        for(User u:q){
            RegSepout regSepout=new RegSepout();
            Patient patient1=patientDao.queryById(u.getPatientId());
            regSepout.setPatient(patient1);
            regSepout.setRegId(u.getRegId());
            regSepouts.add(regSepout);
        }
        return regSepouts;
    }

    //出队
    public List<RegSepout> poll(Long doc_id){
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> q=queueMap.get(doc_id);
        //更新状态为就诊中
        User user=q.poll();
        Registration registration=new Registration();
        registration.setRegId(user.getRegId());
        registration.setState(4);
        registrationDao.update(registration);
        updateSeq(doc_id);
        List<RegSepout> regSepouts=new LinkedList<RegSepout>();
        for(User u:q){
            RegSepout regSepout=new RegSepout();
            Patient patient=patientDao.queryById(u.getPatientId());
            regSepout.setPatient(patient);
            regSepout.setRegId(u.getRegId());
            regSepouts.add(regSepout);
        }
        return regSepouts;
    }

    //更新排队信息
    public void updateSeq(Long doc_id){
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> q=queueMap.get(doc_id);
        int i=1;
        for (User u:q){
            u.setSep(i++);
        }
    }
    //打印队列的信息
    public void  printList(){
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        List<Doctor> doctorList=doctorDao.queryAll();
        for(Doctor doctor:doctorList){
            Queue<User> q=queueMap.get(doctor.getDocId());
            System.out.println("医生id为："+doctor.getDocId());
            for (User u:q){
                System.out.println("挂号id："+u.getRegId()+" 患者id："+u.getPatientId()+" 患者名："+u.getName()+" 当前排队号："+u.getSep());
            }
        }
    }

    //解挂，插入到前5的位置
    public List<RegSepout> unhang(Long reg_id){
        Registration registration=registrationDao.queryById(reg_id);
        Patient patient=patientDao.queryById(registration.getPatientId());
        User user=new User(patient.getPatientId(),reg_id,patient.getPatientName());
        Map<Long,Queue> queueMap=QueueCache.returnMap();
        Queue<User> q=queueMap.get(registration.getDocId());
        //如果生成排序数小于当前排队人数，直接插入到末尾
        if(4>=q.size()){
            push(reg_id);
        }
        //否则插入到随机数的位置
        else{
            List<User> userList=new LinkedList<User>();
            while (q.peek()!=null){
                userList.add(q.poll());
            }
            userList.add(4,user);
            for(int i=0;i<userList.size();i++){
                q.add(userList.get(i));
            }
        }
        updateSeq(registration.getDocId());
        //更新状态为排队中
        Registration reg=new Registration();
        reg.setRegId(reg_id);
        reg.setState(2);
        registrationDao.update(reg);
        //返回数据
        List<RegSepout> regSepouts=new LinkedList<RegSepout>();
        for(User u:q){
            RegSepout regSepout=new RegSepout();
            Patient patient1=patientDao.queryById(u.getPatientId());
            regSepout.setPatient(patient1);
            regSepout.setRegId(u.getRegId());
            regSepouts.add(regSepout);
        }
        return regSepouts;
    }
}
