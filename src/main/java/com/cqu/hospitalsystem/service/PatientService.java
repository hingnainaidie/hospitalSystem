package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Patient)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:01:14
 */
public interface PatientService {

    /**
     * 通过ID查询单条数据
     *
     * @param patientId 主键
     * @return 实例对象
     */
    Patient queryById(Long patientId);
    Patient reg_patient(Long regId);
    Patient searchPatient(Patient patient);
    boolean regByReception(Patient patient);
    /**
     * 分页查询
     *
     * @param patient 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Patient> queryByPage(Patient patient, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param patient 实例对象
     * @return 实例对象
     */
    Patient insert(Patient patient);

    /**
     * 修改数据
     *
     * @param patient 实例对象
     * @return 实例对象
     */
    Patient update(Patient patient);

    /**
     * 通过主键删除数据
     *
     * @param patientId 主键
     * @return 是否成功
     */
    boolean deleteById(Long patientId);

    /**
     * 患者登录
     * @param patient
     * @return
     */
    Patient loginPatient(Patient patient);

    /**
     * 修改患者基本信息
     * @param patient
     * @return
     */
    boolean editInfo(Patient patient);

    /**
     * 查找患者密码
     * @param patientId
     * @return
     */
    Patient searchPwd(long patientId);

    /**
     * 修改患者密码
     * @param patientId
     * @param newPwd
     * @return
     */
    boolean editPwd(long patientId, String newPwd);

    /**
     * 搜索数据库用户，看是否有重复
     * @param patient
     * @return
     */
    Patient searchForPatient(Patient patient);

}
