package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.Vo.prescriptionVo;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Prescription;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Prescription)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:01:48
 */
public interface PrescriptionDao {
    Long getPrescriptionId();
    /**
     * 通过ID查询单条数据
     *
     * @param prescriptionId 主键
     * @return 实例对象
     */
    Prescription queryById(Long prescriptionId);

    /**
     * 查询指定行数据
     *
     * @param prescription 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Prescription> queryAllByLimit(Prescription prescription, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param prescription 查询条件
     * @return 总行数
     */
    long count(Prescription prescription);

    /**
     * 新增数据
     *
     * @param prescription 实例对象
     * @return 影响行数
     */
    int insert(Prescription prescription);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Prescription> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Prescription> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Prescription> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Prescription> entities);

    /**
     * 修改数据
     *
     * @param prescription 实例对象
     * @return 影响行数
     */
    int update(Prescription prescription);

    /**
     * 通过主键删除数据
     *
     * @param prescriptionId 主键
     * @return 影响行数
     */
    int deleteById(Long prescriptionId);

    /**
     * 查看已缴费处方单药品列表
     * @param patient
     * @return
     */
    List<preMedicineVo> showPaidMedicine(Patient patient);

    /**
     * 查看处方单
     * @param prescriptionId
     * @return
     */
    prescriptionVo showMedicine(Long prescriptionId);

    /**
     * 历史处方单列表
     * @param prescriptionId
     * @return
     */
    List<preMedicineVo> showHistoryMedicine(Long prescriptionId);

    /**
     * 未缴费处方单列表
     * @param prescriptionId
     * @return
     */
    List<preMedicineVo> showUnpaidPre(Long prescriptionId);

    /**
     * 交处方单费用
     * @param prescriptionId
     * @return
     */
    boolean payMedicine(Long prescriptionId);

}

