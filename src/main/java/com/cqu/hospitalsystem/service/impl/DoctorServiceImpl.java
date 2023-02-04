package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.DocOffice;
import com.cqu.hospitalsystem.dao.OfficeDao;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.dao.DoctorDao;
import com.cqu.hospitalsystem.entity.Office;
import com.cqu.hospitalsystem.service.DoctorService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Doctor)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 16:59:22
 */
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private OfficeDao officeDao;
    @Resource
    private DoctorDao doctorDao;
    @Resource
    private RegistrationDao registrationDao;

    @Override
    public Doctor loginDoctor(Doctor Doctor) {
        return this.doctorDao.loginDoctor(Doctor);
    }

    @Override
    public boolean editDocPwd(Doctor d){
        return this.doctorDao.editDocPwd(d);
    }
    @Override
    public boolean editDocInfo(Doctor d){
        return this.doctorDao.editDocInfo(d);
    }
    @Override
    public List<Doctor> searchAvailable(Long officeId, Date appointTime){
        List<Doctor> doctorList=this.doctorDao.selectByOffice(officeId);
        System.out.println(doctorList);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(appointTime);
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        Date tomorrow = calendar.getTime();
        System.out.println(appointTime);
        System.out.println(tomorrow);

        for(int i=0;i<doctorList.size();i++){
            Doctor d=doctorList.get(i);
            Long docId=d.getDocId();
            int current=this.registrationDao.countAppoint(docId,appointTime,tomorrow);
            System.out.println(current);

            if(current>=d.getMaxPatient()){
                System.out.println(d.getDocName());
                doctorList.remove(d);
                i--;
            }
        }

        return doctorList;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param docId 主键
     * @return 实例对象
     */
    @Override
    public Doctor queryById(Long docId) {
        return this.doctorDao.queryById(docId);
    }

    /**
     * 分页查询
     *
     * @param doctor 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Doctor> queryByPage(Doctor doctor, PageRequest pageRequest) {
        long total = this.doctorDao.count(doctor);
        return new PageImpl<>(this.doctorDao.queryAllByLimit(doctor, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param doctor 实例对象
     * @return 实例对象
     */
    @Override
    public Doctor insert(Doctor doctor) {
        this.doctorDao.insert(doctor);
        return doctor;
    }

    /**
     * 修改数据
     *
     * @param doctor 实例对象
     * @return 实例对象
     */
    @Override
    public Doctor update(Doctor doctor) {
        this.doctorDao.update(doctor);
        return this.queryById(doctor.getDocId());
    }

    /**
     * 通过主键删除数据
     *
     * @param docId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long docId) {
        return this.doctorDao.deleteById(docId) > 0;
    }

    @Override
    public List<DocOffice> searchDocList(String office_name){
        List<Doctor> doctorList=new LinkedList<Doctor>();
        if(office_name.equals("全部")){   //不能用==,会无效
            doctorList=doctorDao.queryAll();
        }else{
            Office office=officeDao.queryAllByName(office_name);
            Doctor doctor=new Doctor();
            doctor.setOfficeId(office.getOfficeId());
            doctorList=doctorDao.queryAllByAll(doctor);
        }
        List<DocOffice> docOffices=new LinkedList<DocOffice>();
        for(Doctor doc:doctorList){
            Office office1=officeDao.queryById(doc.getOfficeId());
            DocOffice docOffice=new DocOffice();
            docOffice.setDoc_id(doc.getDocId());
            docOffice.setDoc_age(doc.getDocAge());
            docOffice.setDoc_gender(doc.getDocGender());
            docOffice.setDoc_name(doc.getDocName());
            docOffice.setOffice_name(office1.getOfficeName());
            docOffices.add(docOffice);
        }
        return docOffices;
    }

    @Override
    public DocOffice docDetail(Long doc_id){
        Doctor doctor=doctorDao.queryById(doc_id);
        Office office=officeDao.queryById(doctor.getOfficeId());
        DocOffice docOffice=new DocOffice();
        docOffice.setOffice_name(office.getOfficeName());
        docOffice.setDoc_id(doctor.getDocId());
        docOffice.setDoc_level(doctor.getDocLevel());
        docOffice.setDoc_name(doctor.getDocName());
        docOffice.setDoc_age(doctor.getDocAge());
        docOffice.setDoc_price(doctor.getDocPrice());
        docOffice.setDoc_phone(doctor.getDocPhone());
        return docOffice;
    }
}
