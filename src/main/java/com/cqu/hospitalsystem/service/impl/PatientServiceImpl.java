package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.dao.PatientDao;
import com.cqu.hospitalsystem.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Patient)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:01:14
 */
@Service("patientService")
public class PatientServiceImpl implements PatientService {
    @Resource
    private PatientDao patientDao;

    @Override
    public Patient reg_patient(Long regId){
        return this.patientDao.reg_patient(regId);
    }

    @Override
    public Patient searchPatient(Patient patient) {
        return this.patientDao.searchPatient(patient);
    }
    @Override
    public boolean regByReception(Patient patient) {
        String identify=patient.getPatientIdentify();
        //初始密码为身份证后六位
        String password=identify.substring(identify.length()-6);
        patient.setPatientPassword(password);
        int res=this.patientDao.insert(patient);
        if(res!=0){
            return true;
        }
        return false;
    }


    /**
     * 通过ID查询单条数据
     *
     * @param patientId 主键
     * @return 实例对象
     */
    @Override
    public Patient queryById(Long patientId) {
        return this.patientDao.queryById(patientId);
    }

    /**
     * 分页查询
     *
     * @param patient 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Patient> queryByPage(Patient patient, PageRequest pageRequest) {
        long total = this.patientDao.count(patient);
        return new PageImpl<>(this.patientDao.queryAllByLimit(patient, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param patient 实例对象
     * @return 实例对象
     */
    @Override
    public Patient insert(Patient patient) {
        this.patientDao.insert(patient);
        return patient;
    }

    /**
     * 修改数据
     *
     * @param patient 实例对象
     * @return 实例对象
     */
    @Override
    public Patient update(Patient patient) {
        this.patientDao.update(patient);
        return this.queryById(patient.getPatientId());
    }

    /**
     * 通过主键删除数据
     *
     * @param patientId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long patientId) {
        return this.patientDao.deleteById(patientId) > 0;
    }

    @Override
    public Patient loginPatient(Patient patient) {
        return this.patientDao.loginPatient(patient);
    }

    @Override
    public boolean editInfo(Patient patient) {
        return this.patientDao.editInfo(patient);
    }

    @Override
    public Patient searchPwd(long patientId) {
        return this.patientDao.searchPwd(patientId);
    }

    @Override
    public boolean editPwd(long patientId, String newPwd) {
        return this.patientDao.editPwd(patientId,newPwd);
    }

    @Override
    public Patient searchForPatient(Patient patient) {
        return this.patientDao.searchForPatient(patient);
    }
}
