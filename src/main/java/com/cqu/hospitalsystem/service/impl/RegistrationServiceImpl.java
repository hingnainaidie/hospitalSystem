package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.DocReg;
import com.cqu.hospitalsystem.Vo.historyRegVo;
import com.cqu.hospitalsystem.Vo.undoUnpaidVo;
import com.cqu.hospitalsystem.Vo.undoVo;
import com.cqu.hospitalsystem.dao.DoctorDao;
import com.cqu.hospitalsystem.dao.PatientDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * (Registration)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:02:19
 */
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    @Resource
    private RegistrationDao registrationDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private DoctorDao doctorDao;

    public RegistrationDao getRegistrationDao() {
        return registrationDao;
    }
    @Override
    public boolean endVisit(Registration registration){
        return this.registrationDao.endVisit(registration);
    }

    @Override
    public boolean suspendVisit(Registration registration){
        return this.registrationDao.suspendVisit(registration);
    }

    @Override
    public List<DocReg> searchReg(Patient patient){
        Patient p=this.patientDao.searchPatient(patient);
        Long pId=p.getPatientId();
        System.out.println(pId);
        List<DocReg> docRegList = new LinkedList<DocReg>();
        List<Registration> regList=this.registrationDao.queryByPatient(pId,3);

        for(Registration r:regList){
            DocReg docReg=new DocReg();
            docReg.setRegId(r.getRegId());
            docReg.setRegTime(r.getRegTime());
            docReg.setAppointmentTime(r.getAppointmentTime());
            docReg.setDocId(r.getDocId());
            Doctor d=this.doctorDao.queryById(r.getDocId());
            docReg.setDocName(d.getDocName());
            docRegList.add(docReg);
            System.out.println(docReg);
        }
        return docRegList;

    }
    @Override
    public Registration visiting(Doctor doctor){
        return this.registrationDao.visiting(doctor.getDocId(),4);
    }
    @Override
    public List<Patient> searchPatient(Registration r){
        Long pId=r.getPatientId();
        List<Patient> list=new LinkedList<>();
        list.add(this.patientDao.queryById(pId));
        return list;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param regId 主键
     * @return 实例对象
     */
    @Override
    public Registration queryById(Long regId) {
        return this.registrationDao.queryById(regId);
    }

    /**
     * 分页查询
     *
     * @param registration 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Registration> queryByPage(Registration registration, PageRequest pageRequest) {
        long total = this.registrationDao.count(registration);
        return new PageImpl<>(this.registrationDao.queryAllByLimit(registration, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param registration 实例对象
     * @return 实例对象
     */
    @Override
    public Registration insert(Registration registration) {
        this.registrationDao.insert(registration);
        return registration;
    }

    /**
     * 修改数据
     *
     * @param registration 实例对象
     * @return 实例对象
     */
    @Override
    public Registration update(Registration registration) {
        this.registrationDao.update(registration);
        return this.queryById(registration.getRegId());
    }

    /**
     * 通过主键删除数据
     *
     * @param regId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long regId) {
        return this.registrationDao.deleteById(regId) > 0;
    }

    /**
     * 查看历史预约信息列表
     * @param patientId
     * @return
     */
    @Override
    public List<historyRegVo> showHistory(Long patientId) {
        int size = this.registrationDao.showHistory(patientId).size();
        System.out.println("列表长度："+size);
        return this.registrationDao.showHistory(patientId);
    }

    /**
     * 历史预约详情
     * @param idRelationId
     * @return
     */
    @Override
    public historyRegVo historyDetail(Long idRelationId) {
        return this.registrationDao.historyDetail(idRelationId);
    }

    @Override
    public List<undoVo> showUndoList(Long patientId) {
        return this.registrationDao.showUndoList(patientId);
    }

    /**
     * 未完成预约详情
     * @param regId
     * @return
     */
    @Override
    public undoVo showUndoDetail(Long regId) {
        return this.registrationDao.showUndoDetail(regId);
    }

    /**
     * 就诊中未缴费详情
     * @param regId
     * @return
     */
    @Override
    public undoUnpaidVo showUnpaidDetail(Long regId) {
        return this.registrationDao.showUnpaidDetail(regId);
    }

    /**
     * 支付挂号单费
     * @param regId
     * @return
     */
    @Override
    public boolean payOrder(Long regId) {
        return this.registrationDao.payOrder(regId);
    }

    /**
     * 取消挂号
     * @param regId
     * @return
     */
    @Override
    public boolean cancelOrder(Long regId) {
        return this.registrationDao.cancelOrder(regId);
    }
}
