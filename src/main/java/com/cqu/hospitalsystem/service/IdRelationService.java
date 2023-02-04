package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.IdRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (IdRelation)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 16:59:40
 */
public interface IdRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param idRelationId 主键
     * @return 实例对象
     */
    IdRelation queryById(Long idRelationId);

    /**
     * 分页查询
     *
     * @param idRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IdRelation> queryByPage(IdRelation idRelation, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param idRelation 实例对象
     * @return 实例对象
     */
    IdRelation insert(IdRelation idRelation);

    /**
     * 修改数据
     *
     * @param idRelation 实例对象
     * @return 实例对象
     */
    IdRelation update(IdRelation idRelation);

    /**
     * 通过主键删除数据
     *
     * @param idRelationId 主键
     * @return 是否成功
     */
    boolean deleteById(Long idRelationId);

}
