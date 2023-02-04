package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.Vo.historyRegVo;
import com.cqu.hospitalsystem.Vo.undoUnpaidVo;
import com.cqu.hospitalsystem.Vo.undoVo;
import com.cqu.hospitalsystem.entity.Registration;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * (Registration)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:02:19
 */
public interface RegistrationDao {
    boolean endVisit(Registration registration);
    boolean suspendVisit(Registration registration);
    List<Registration> queryByPatient(@Param("patientId") Long patientId,@Param("state") Integer state);
    int countAppoint(@Param("docId")Long docId, @Param("appointTime") Date appointTime, @Param("tomorrow") Date tomorrow);
    Registration visiting(@Param("docId") Long docId,@Param("state") Integer state);

    /**
     * 通过ID查询单条数据
     *
     * @param regId 主键
     * @return 实例对象
     */
    Registration queryById(Long regId);

    List<Registration> queryAll();

    /**
     * 查询指定行数据
     *
     * @param registration 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Registration> queryAllByLimit(Registration registration, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param registration 查询条件
     * @return 总行数
     */
    long count(Registration registration);

    /**
     * 新增数据
     *
     * @param registration 实例对象
     * @return 影响行数
     */
    int insert(Registration registration);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Registration> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Registration> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Registration> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Registration> entities);

    /**
     * 修改数据
     *
     * @param registration 实例对象
     * @return 影响行数
     */
    int update(Registration registration);

    /**
     * 通过主键删除数据
     *
     * @param regId 主键
     * @return 影响行数
     */
    int deleteById(Long regId);

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

    /**
     * 通过医生查预约信息
     * @param docId
     * @return
     */
    List<Registration> queryAllByDoctor(Long docId,int state);


}

