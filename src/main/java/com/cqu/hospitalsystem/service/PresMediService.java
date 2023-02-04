package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.PresMedi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (PresMedi)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:01:29
 */
public interface PresMediService {

    /**
     * 通过ID查询单条数据
     *
     * @param connectId 主键
     * @return 实例对象
     */
    PresMedi queryById(Long connectId);

    /**
     * 分页查询
     *
     * @param presMedi 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<PresMedi> queryByPage(PresMedi presMedi, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param presMedi 实例对象
     * @return 实例对象
     */
    PresMedi insert(PresMedi presMedi);

    /**
     * 修改数据
     *
     * @param presMedi 实例对象
     * @return 实例对象
     */
    PresMedi update(PresMedi presMedi);

    /**
     * 通过主键删除数据
     *
     * @param connectId 主键
     * @return 是否成功
     */
    boolean deleteById(Long connectId);

}
