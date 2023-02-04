package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.Recordtemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Recordtemplate)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:02:04
 */
public interface RecordtemplateDao {

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
     * 查询指定行数据
     *
     * @param recordtemplate 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Recordtemplate> queryAllByLimit(Recordtemplate recordtemplate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param recordtemplate 查询条件
     * @return 总行数
     */
    long count(Recordtemplate recordtemplate);

    /**
     * 新增数据
     *
     * @param recordtemplate 实例对象
     * @return 影响行数
     */
    int insert(Recordtemplate recordtemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Recordtemplate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Recordtemplate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Recordtemplate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Recordtemplate> entities);

    /**
     * 修改数据
     *
     * @param recordtemplate 实例对象
     * @return 影响行数
     */
    int update(Recordtemplate recordtemplate);

    /**
     * 通过主键删除数据
     *
     * @param rtemplateId 主键
     * @return 影响行数
     */
    int deleteById(Long rtemplateId);

}

