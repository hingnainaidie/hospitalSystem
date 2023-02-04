package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.MediPres;
import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.Vo.prescriptionVo;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.util.List;

/**
 * (Prescription)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:01:48
 */
public interface PrescriptionService {

    /**
     * 通过ID查询单条数据
     *
     * @param prescriptionId 主键
     * @return 实例对象
     */
    Prescription queryById(Long prescriptionId);
    boolean savePrescription(MediPres medipres) throws ParseException;
    /**
     * 分页查询
     *
     * @param prescription 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Prescription> queryByPage(Prescription prescription, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param prescription 实例对象
     * @return 实例对象
     */
    Prescription insert(Prescription prescription);

    /**
     * 修改数据
     *
     * @param prescription 实例对象
     * @return 实例对象
     */
    Prescription update(Prescription prescription);

    /**
     * 通过主键删除数据
     *
     * @param prescriptionId 主键
     * @return 是否成功
     */
    boolean deleteById(Long prescriptionId);

    /**
     * 显示已缴费处方单药品列表
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
     * 历史处方单药品列表
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
