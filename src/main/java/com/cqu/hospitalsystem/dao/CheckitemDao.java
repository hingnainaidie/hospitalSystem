package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checkitem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Checkitem)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 16:58:19
 */
public interface CheckitemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param checkitemId 主键
     * @return 实例对象
     */
    Checkitem queryById(Long checkitemId);
    List<Checkitem> allCheckitem();
    /**
     * 查询指定行数据
     *
     * @param checkitem 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Checkitem> queryAllByLimit(Checkitem checkitem, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param checkitem 查询条件
     * @return 总行数
     */
    long count(Checkitem checkitem);

    /**
     * 新增数据
     *
     * @param checkitem 实例对象
     * @return 影响行数
     */
    int insert(Checkitem checkitem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checkitem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Checkitem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checkitem> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Checkitem> entities);

    /**
     * 修改数据
     *
     * @param checkitem 实例对象
     * @return 影响行数
     */
    int update(Checkitem checkitem);

    /**
     * 通过主键删除数据
     *
     * @param checkitemId 主键
     * @return 影响行数
     */
    int deleteById(Long checkitemId);

    /**
     * 取消检查项目
     * @param preCheck
     * @return
     */
    boolean cancelCheck(preCheck preCheck);

}

