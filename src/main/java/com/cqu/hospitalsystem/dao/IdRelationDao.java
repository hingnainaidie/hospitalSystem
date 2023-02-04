package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.IdRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (IdRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 16:59:40
 */
public interface IdRelationDao {
    IdRelation findbyregId(@Param("regId") Long regID);//查找id关联表里有没有记录
    boolean insert_idrelation(@Param("regId") Long regID,@Param("mrecordId") Long mrecordId);
    boolean update_mrecordId(@Param("regId") Long regID,@Param("mrecordId") Long mrecordId);
    boolean insert_idRePres(@Param("regId") Long regID,@Param("prescriptionId") Long prescriptionId);
    boolean update_idRePres(@Param("regId") Long regID,@Param("prescriptionId") Long prescriptionId);
    boolean insert_idRecheck(@Param("regId") Long regID,@Param("checklistId") Long checklistId);
    boolean update_idRecheck(@Param("regId") Long regID,@Param("checklistId") Long checklistId);
    /**
     * 通过ID查询单条数据
     *
     * @param idRelationId 主键
     * @return 实例对象
     */
    IdRelation queryById(Long idRelationId);

    /**
     * 查询指定行数据
     *
     * @param idRelation 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<IdRelation> queryAllByLimit(IdRelation idRelation, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param idRelation 查询条件
     * @return 总行数
     */
    long count(IdRelation idRelation);

    /**
     * 新增数据
     *
     * @param idRelation 实例对象
     * @return 影响行数
     */
    int insert(IdRelation idRelation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IdRelation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IdRelation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IdRelation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IdRelation> entities);

    /**
     * 修改数据
     *
     * @param idRelation 实例对象
     * @return 影响行数
     */
    int update(IdRelation idRelation);

    /**
     * 通过主键删除数据
     *
     * @param idRelationId 主键
     * @return 影响行数
     */
    int deleteById(Long idRelationId);

}

