package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.IdRelation;
import com.cqu.hospitalsystem.dao.IdRelationDao;
import com.cqu.hospitalsystem.service.IdRelationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (IdRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 16:59:40
 */
@Service("idRelationService")
public class IdRelationServiceImpl implements IdRelationService {
    @Resource
    private IdRelationDao idRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param idRelationId 主键
     * @return 实例对象
     */
    @Override
    public IdRelation queryById(Long idRelationId) {
        return this.idRelationDao.queryById(idRelationId);
    }

    /**
     * 分页查询
     *
     * @param idRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IdRelation> queryByPage(IdRelation idRelation, PageRequest pageRequest) {
        long total = this.idRelationDao.count(idRelation);
        return new PageImpl<>(this.idRelationDao.queryAllByLimit(idRelation, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param idRelation 实例对象
     * @return 实例对象
     */
    @Override
    public IdRelation insert(IdRelation idRelation) {
        this.idRelationDao.insert(idRelation);
        return idRelation;
    }

    /**
     * 修改数据
     *
     * @param idRelation 实例对象
     * @return 实例对象
     */
    @Override
    public IdRelation update(IdRelation idRelation) {
        this.idRelationDao.update(idRelation);
        return this.queryById(idRelation.getIdRelationId());
    }

    /**
     * 通过主键删除数据
     *
     * @param idRelationId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long idRelationId) {
        return this.idRelationDao.deleteById(idRelationId) > 0;
    }
}
