package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checkitem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Checkitem)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 16:58:21
 */
public interface CheckitemService {

    /**
     * 通过ID查询单条数据
     *
     * @param checkitemId 主键
     * @return 实例对象
     */
    Checkitem queryById(Long checkitemId);
    List<Checkitem> allCheckitem();
    /**
     * 分页查询
     *
     * @param checkitem 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Checkitem> queryByPage(Checkitem checkitem, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param checkitem 实例对象
     * @return 实例对象
     */
    Checkitem insert(Checkitem checkitem);

    /**
     * 修改数据
     *
     * @param checkitem 实例对象
     * @return 实例对象
     */
    Checkitem update(Checkitem checkitem);

    /**
     * 通过主键删除数据
     *
     * @param checkitemId 主键
     * @return 是否成功
     */
    boolean deleteById(Long checkitemId);

    /**
     * 取消检查项目
     * @param preCheck
     * @return
     */
    boolean cancelCheck(preCheck preCheck);
}
