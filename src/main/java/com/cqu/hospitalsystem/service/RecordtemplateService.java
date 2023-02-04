package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.Recordtemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Recordtemplate)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:02:04
 */
public interface RecordtemplateService {

    /**
     * 通过ID查询单条数据
     *
     * @param rtemplateId 主键
     * @return 实例对象
     */
    Recordtemplate queryById(Long rtemplateId);
    Recordtemplate searchTemplate(Recordtemplate recordtemplate);
    boolean saveTemplate(Recordtemplate recordtemplate);
    /**
     * 分页查询
     *
     * @param recordtemplate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Recordtemplate> queryByPage(Recordtemplate recordtemplate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param recordtemplate 实例对象
     * @return 实例对象
     */
    Recordtemplate insert(Recordtemplate recordtemplate);

    /**
     * 修改数据
     *
     * @param recordtemplate 实例对象
     * @return 实例对象
     */
    Recordtemplate update(Recordtemplate recordtemplate);

    /**
     * 通过主键删除数据
     *
     * @param rtemplateId 主键
     * @return 是否成功
     */
    boolean deleteById(Long rtemplateId);

}
