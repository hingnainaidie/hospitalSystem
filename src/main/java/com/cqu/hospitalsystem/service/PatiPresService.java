package com.cqu.hospitalsystem.service;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PatiPresService {

    /**
     * 分页查询
     *
     * @param patiPresRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<PatiPresRelation> queryByPage(PatiPresRelation patiPresRelation, PageRequest pageRequest);
    List<PatiPresRelation> queryAll();
    List<PatiPresRelation> queryById(String patientIdentify);

    List<PatiPresRelation> queryByPId(Long prescriptionId);

    PatiPresRelation update(PatiPresRelation patiPresRelation);

    PatiPresRelation update_mediState(PatiPresRelation patiPresRelation);

    List<PatiPresRelation> queryreturnAll();
}
