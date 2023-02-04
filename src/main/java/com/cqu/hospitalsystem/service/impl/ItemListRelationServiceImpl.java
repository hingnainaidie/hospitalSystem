package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.ItemListRelation;
import com.cqu.hospitalsystem.dao.ItemListRelationDao;
import com.cqu.hospitalsystem.service.ItemListRelationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ItemListRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 16:59:59
 */
@Service("itemListRelationService")
public class ItemListRelationServiceImpl implements ItemListRelationService {
    @Resource
    private ItemListRelationDao itemListRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param itemListId 主键
     * @return 实例对象
     */
    @Override
    public ItemListRelation queryById(Long itemListId) {
        return this.itemListRelationDao.queryById(itemListId);
    }

    /**
     * 分页查询
     *
     * @param itemListRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ItemListRelation> queryByPage(ItemListRelation itemListRelation, PageRequest pageRequest) {
        long total = this.itemListRelationDao.count(itemListRelation);
        return new PageImpl<>(this.itemListRelationDao.queryAllByLimit(itemListRelation, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param itemListRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ItemListRelation insert(ItemListRelation itemListRelation) {
        this.itemListRelationDao.insert(itemListRelation);
        return itemListRelation;
    }

    /**
     * 修改数据
     *
     * @param itemListRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ItemListRelation update(ItemListRelation itemListRelation) {
        this.itemListRelationDao.update(itemListRelation);
        return this.queryById(itemListRelation.getItemListId());
    }

    /**
     * 通过主键删除数据
     *
     * @param itemListId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long itemListId) {
        return this.itemListRelationDao.deleteById(itemListId) > 0;
    }
}
