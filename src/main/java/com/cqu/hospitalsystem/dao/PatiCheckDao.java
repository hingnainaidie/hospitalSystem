package com.cqu.hospitalsystem.dao;
import com.cqu.hospitalsystem.entity.PatiCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface PatiCheckDao {
    List<PatiCheck> queryAll();
    List<PatiCheck> queryById(String patientIdentify);

    List<PatiCheck> queryByPId(Long clistId);

    int update(PatiCheck patiCheck);

    int update_checkState(PatiCheck patiCheck);

    int update_itemState(PatiCheck patiCheck);
}
