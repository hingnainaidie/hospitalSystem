package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.ItemListRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ItemListRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 16:59:59
 */
public interface ItemListRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param itemListId 主键
     * @return 实例对象
     */
    ItemListRelation queryById(Long itemListId);
    boolean insertItemList(@Param("checkitemId") Long checkitemId, @Param("checklistId") Long checklistId, @Param("state") Integer state);
    /**
     * 查询指定行数据
     *
     * @param itemListRelation 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ItemListRelation> queryAllByLimit(ItemListRelation itemListRelation, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param itemListRelation 查询条件
     * @return 总行数
     */
    long count(ItemListRelation itemListRelation);

    /**
     * 新增数据
     *
     * @param itemListRelation 实例对象
     * @return 影响行数
     */
    int insert(ItemListRelation itemListRelation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ItemListRelation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ItemListRelation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ItemListRelation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ItemListRelation> entities);

    /**
     * 修改数据
     *
     * @param itemListRelation 实例对象
     * @return 影响行数
     */
    int update(ItemListRelation itemListRelation);

    /**
     * 通过主键删除数据
     *
     * @param itemListId 主键
     * @return 影响行数
     */
    int deleteById(Long itemListId);

}

