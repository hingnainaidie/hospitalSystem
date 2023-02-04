package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.Patient;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Patient)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:01:14
 */
public interface PatientDao {

    Patient reg_patient(Long regId);
    Patient searchPatient(Patient patient);
    /**
     * 通过ID查询单条数据
     *
     * @param patientId 主键
     * @return 实例对象
     */
    Patient queryById(Long patientId);

    /**
     * 查询指定行数据
     *
     * @param patient 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Patient> queryAllByLimit(Patient patient, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param patient 查询条件
     * @return 总行数
     */
    long count(Patient patient);

    /**
     * 新增数据
     *
     * @param patient 实例对象
     * @return 影响行数
     */
    int insert(Patient patient);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Patient> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Patient> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Patient> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Patient> entities);

    /**
     * 修改数据
     *
     * @param patient 实例对象
     * @return 影响行数
     */
    int update(Patient patient);

    /**
     * 通过主键删除数据
     *
     * @param patientId 主键
     * @return 影响行数
     */
    int deleteById(Long patientId);

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

