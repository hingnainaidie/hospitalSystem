package com.cqu.hospitalsystem.dao;

import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.entity.Medicine;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Medicine)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-28 17:00:37
 */
public interface MedicineDao {

    /**
     * 通过ID查询单条数据
     *
     * @param medicineId 主键
     * @return 实例对象
     */
    Medicine queryById(Long medicineId);
    List<Medicine> allMedicine();
    /**
     * 查询指定行数据
     *
     * @param medicine 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Medicine> queryAllByLimit(Medicine medicine, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param medicine 查询条件
     * @return 总行数
     */
    long count(Medicine medicine);

    /**
     * 新增数据
     *
     * @param medicine 实例对象
     * @return 影响行数
     */
    int insert(Medicine medicine);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Medicine> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Medicine> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Medicine> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Medicine> entities);

    /**
     * 修改数据
     *
     * @param medicine 实例对象
     * @return 影响行数
     */
    int update(Medicine medicine);

    /**
     * 通过主键删除数据
     *
     * @param medicineId 主键
     * @return 影响行数
     */
    int deleteById(Long medicineId);

    /**
     * 退药
     * @param preMedicineVo
     * @return
     */
    boolean cancelMedicine(preMedicineVo preMedicineVo);

}

