package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.DocReg;
import com.cqu.hospitalsystem.Vo.historyRegVo;
import com.cqu.hospitalsystem.Vo.undoUnpaidVo;
import com.cqu.hospitalsystem.Vo.undoVo;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Registration)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:02:19
 */
public interface RegistrationService {

    /**
     * 通过ID查询单条数据
     *
     * @param regId 主键
     * @return 实例对象
     */
    Registration queryById(Long regId);
    boolean endVisit(Registration registration);
    boolean suspendVisit(Registration registration);
    List<DocReg> searchReg(Patient patient);
    Registration visiting(Doctor doctor);
    List<Patient> searchPatient(Registration r);
    /**
     * 分页查询
     *
     * @param registration 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Registration> queryByPage(Registration registration, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param registration 实例对象
     * @return 实例对象
     */
    Registration insert(Registration registration);

    /**
     * 修改数据
     *
     * @param registration 实例对象
     * @return 实例对象
     */
    Registration update(Registration registration);

    /**
     * 通过主键删除数据
     *
     * @param regId 主键
     * @return 是否成功
     */
    boolean deleteById(Long regId);

    /**
     * 查看历史预约信息
     * @param patientId
     * @return
     */
    List<historyRegVo> showHistory(Long patientId);

    /**
     * 查看历史预约详情
     * @param idRelationId
     * @return
     */
    historyRegVo historyDetail(Long idRelationId);

    /**
     * 未完成预约列表
     * @param patientId
     * @return
     */
    List<undoVo> showUndoList(Long patientId);

    /**
     * 未完成预约详情
     * @param regId
     * @return
     */
    undoVo showUndoDetail(Long regId);

    /**
     *就诊中未缴费详情
     * @param regId
     * @return
     */
    undoUnpaidVo showUnpaidDetail(Long regId);

    /**
     * 支付挂号单费
     * @param regId
     * @return
     */
    boolean payOrder(Long regId);

    /**
     * 取消挂号
     * @param regId
     * @return
     */
    boolean cancelOrder(Long regId);


}
