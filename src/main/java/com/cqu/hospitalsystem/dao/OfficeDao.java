package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.entity.Office;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Office)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:00:57
 */
public interface OfficeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param officeId 主键
     * @return 实例对象
     */
    Office queryById(Long officeId);
    public List<Office> allOffice();
    /**
     * 查询指定行数据
     *
     * @param office 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Office> queryAllByLimit(Office office, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param office 查询条件
     * @return 总行数
     */
    long count(Office office);

    /**
     * 新增数据
     *
     * @param office 实例对象
     * @return 影响行数
     */
    int insert(Office office);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Office> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Office> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Office> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Office> entities);

    /**
     * 修改数据
     *
     * @param office 实例对象
     * @return 影响行数
     */
    int update(Office office);

    /**
     * 通过主键删除数据
     *
     * @param officeId 主键
     * @return 影响行数
     */
    int deleteById(Long officeId);

    Office queryAllByName(String officeName);
}

