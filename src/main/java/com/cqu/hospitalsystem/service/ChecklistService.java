package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.Check;
import com.cqu.hospitalsystem.Vo.checklistVo;
import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checklist;
import com.cqu.hospitalsystem.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.util.List;

/**
 * (Checklist)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 16:58:52
 */
public interface ChecklistService {

    /**
     * 通过ID查询单条数据
     *
     * @param checklistId 主键
     * @return 实例对象
     */
    Checklist queryById(Long checklistId);
    boolean saveChecklist(Check check) throws ParseException;
    /**
     * 分页查询
     *
     * @param checklist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Checklist> queryByPage(Checklist checklist, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param checklist 实例对象
     * @return 实例对象
     */
    Checklist insert(Checklist checklist);

    /**
     * 修改数据
     *
     * @param checklist 实例对象
     * @return 实例对象
     */
    Checklist update(Checklist checklist);

    /**
     * 通过主键删除数据
     *
     * @param checklistId 主键
     * @return 是否成功
     */
    boolean deleteById(Long checklistId);

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
     * 显示历史检查单列表
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
