package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.entity.ItemListRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ItemListRelation)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 16:59:59
 */
public interface ItemListRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param itemListId 主键
     * @return 实例对象
     */
    ItemListRelation queryById(Long itemListId);

    /**
     * 分页查询
     *
     * @param itemListRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ItemListRelation> queryByPage(ItemListRelation itemListRelation, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param itemListRelation 实例对象
     * @return 实例对象
     */
    ItemListRelation insert(ItemListRelation itemListRelation);

    /**
     * 修改数据
     *
     * @param itemListRelation 实例对象
     * @return 实例对象
     */
    ItemListRelation update(ItemListRelation itemListRelation);

    /**
     * 通过主键删除数据
     *
     * @param itemListId 主键
     * @return 是否成功
     */
    boolean deleteById(Long itemListId);

}
