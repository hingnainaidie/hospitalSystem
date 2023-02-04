package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Office)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:00:57
 */
public interface OfficeService {

    /**
     * 通过ID查询单条数据
     *
     * @param officeId 主键
     * @return 实例对象
     */
    Office queryById(Long officeId);
    List<Office> allOffice();
    /**
     * 分页查询
     *
     * @param office 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Office> queryByPage(Office office, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param office 实例对象
     * @return 实例对象
     */
    Office insert(Office office);

    /**
     * 修改数据
     *
     * @param office 实例对象
     * @return 实例对象
     */
    Office update(Office office);

    /**
     * 通过主键删除数据
     *
     * @param officeId 主键
     * @return 是否成功
     */
    boolean deleteById(Long officeId);

}
