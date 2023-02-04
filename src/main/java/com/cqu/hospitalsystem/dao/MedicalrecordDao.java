package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.Medicalrecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Medicalrecord)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:00:15
 */
public interface MedicalrecordDao {
    Long getMedicalID();
    /**
     * 通过ID查询单条数据
     *
     * @param mrecordId 主键
     * @return 实例对象
     */
    Medicalrecord queryById(Long mrecordId);

    /**
     * 查询指定行数据
     *
     * @param medicalrecord 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Medicalrecord> queryAllByLimit(Medicalrecord medicalrecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param medicalrecord 查询条件
     * @return 总行数
     */
    long count(Medicalrecord medicalrecord);

    /**
     * 新增数据
     *
     * @param medicalrecord 实例对象
     * @return 影响行数
     */
    int insert(Medicalrecord medicalrecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Medicalrecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Medicalrecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Medicalrecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Medicalrecord> entities);

    /**
     * 修改数据
     *
     * @param medicalrecord 实例对象
     * @return 影响行数
     */
    int update(Medicalrecord medicalrecord);

    /**
     * 通过主键删除数据
     *
     * @param mrecordId 主键
     * @return 影响行数
     */
    int deleteById(Long mrecordId);

}

