package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.PresMedi;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (PresMedi)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:01:29
 */
public interface PresMediDao {

    /**
     * 通过ID查询单条数据
     *
     * @param connectId 主键
     * @return 实例对象
     */
    PresMedi queryById(Long connectId);

    /**
     * 查询指定行数据
     *
     * @param presMedi 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PresMedi> queryAllByLimit(PresMedi presMedi, @Param("pageable") Pageable pageable);
    boolean insertPresMedi(@Param("prescriptionId") Long prescriptionId, @Param("medicineId") Long medicineId,@Param("amount") Integer amount, @Param("state") Integer state);
    /**
     * 统计总行数
     *
     * @param presMedi 查询条件
     * @return 总行数
     */
    long count(PresMedi presMedi);

    /**
     * 新增数据
     *
     * @param presMedi 实例对象
     * @return 影响行数
     */
    int insert(PresMedi presMedi);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PresMedi> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PresMedi> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PresMedi> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PresMedi> entities);

    /**
     * 修改数据
     *
     * @param presMedi 实例对象
     * @return 影响行数
     */
    int update(PresMedi presMedi);

    /**
     * 通过主键删除数据
     *
     * @param connectId 主键
     * @return 影响行数
     */
    int deleteById(Long connectId);

}

