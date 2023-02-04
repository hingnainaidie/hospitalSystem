package com.cqu.hospitalsystem.dao;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import com.cqu.hospitalsystem.entity.PresMedi;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatiPresDao {
    /**
     * 查询指定行数据
     *
     * @param patiPres 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PatiPresRelation> queryAllByLimit(PatiPresRelation patiPres, @Param("pageable") Pageable pageable);
    /**
     * 统计总行数
     *
     * @param patiPresRelation 查询条件
     * @return 总行数
     */
    long count(PatiPresRelation patiPresRelation);
    List<PatiPresRelation> queryAll();
    List<PatiPresRelation> queryById(String patientIdentify);

    List<PatiPresRelation> queryByPId(Long prescriptionId);

    int update(PatiPresRelation patiPresRelation);

    int update_mediState(PatiPresRelation patiPresRelation);

    List<PatiPresRelation> queryreturnAll();
}
