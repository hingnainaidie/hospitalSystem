package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.Vo.checklistVo;
import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checklist;
import com.cqu.hospitalsystem.entity.Patient;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Checklist)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 16:58:52
 */
public interface ChecklistDao {

    /**
     * 通过ID查询单条数据
     *
     * @param checklistId 主键
     * @return 实例对象
     */
    Checklist queryById(Long checklistId);
    Long getChecklistId();
    /**
     * 查询指定行数据
     *
     * @param checklist 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Checklist> queryAllByLimit(Checklist checklist, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param checklist 查询条件
     * @return 总行数
     */
    long count(Checklist checklist);

    /**
     * 新增数据
     *
     * @param checklist 实例对象
     * @return 影响行数
     */
    int insert(Checklist checklist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checklist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Checklist> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checklist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Checklist> entities);

    /**
     * 修改数据
     *
     * @param checklist 实例对象
     * @return 影响行数
     */
    int update(Checklist checklist);

    /**
     * 通过主键删除数据
     *
     * @param checklistId 主键
     * @return 影响行数
     */
    int deleteById(Long checklistId);


    /**
     * 显示已缴费检查单列表
     * @param patient
     * @return
     */
    List<preCheck> showPaidCheck(Patient patient);

    /**
     *查看历史检查单
     * @param checklistId
     * @return
     */
    checklistVo showCheck(Long checklistId);

    /**
     * 显示历史检查单检查项目列表
     * @param checklistId
     * @return
     */
    List<preCheck> showHistoryCheck(Long checklistId);

    /**
     * 未缴费检查单列表
     * @param checklistId
     * @return
     */
    List<preCheck> showUnpaidCheck(Long checklistId);

    /**
     * 支付检查单
     * @param checklistId
     * @return
     */
    boolean payCheck(Long checklistId);

}

