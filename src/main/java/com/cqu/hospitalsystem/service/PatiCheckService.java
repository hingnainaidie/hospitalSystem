package com.cqu.hospitalsystem.service;
import com.cqu.hospitalsystem.entity.PatiCheck;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
public interface PatiCheckService {
    //Page<PatiCheck> queryByPage(PatiCheck patiPresRelation, PageRequest pageRequest);
    List<PatiCheck> queryAll();
    List<PatiCheck> queryById(String patientIdentify);

    List<PatiCheck> queryByPId(Long prescriptionId);

    PatiCheck update(PatiCheck patiCheck);

    PatiCheck update_checkState(PatiCheck patiCheck);

    PatiCheck update_itemState(PatiCheck patiCheck);
}
