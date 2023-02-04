package com.cqu.hospitalsystem.service.impl;
import com.cqu.hospitalsystem.entity.PatiCheck;
import com.cqu.hospitalsystem.dao.PatiCheckDao;
import com.cqu.hospitalsystem.service.PatiCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("PatiCheckService")
public class PatiCheckServiceImpl implements PatiCheckService {
    @Resource
    private PatiCheckDao patiCheckDao;
    @Override
    public List<PatiCheck> queryAll() {
        return this.patiCheckDao.queryAll();
    }

    @Override
    public List<PatiCheck> queryById(String patientIdentify) {
        System.out.println(patientIdentify);
        return this.patiCheckDao.queryById(patientIdentify);
    }

    @Override
    public List<PatiCheck> queryByPId(Long clistId) {
        return this.patiCheckDao.queryByPId(clistId);
    }

    @Override
    public PatiCheck update(PatiCheck patiCheck) {
        this.patiCheckDao.update(patiCheck);
        return this.queryByPId(patiCheck.getClistId()).get(0);
    }

    @Override
    public PatiCheck update_checkState(PatiCheck patiCheck) {
        this.patiCheckDao.update_checkState(patiCheck);
        return this.queryByPId(patiCheck.getClistId()).get(0);
    }
    @Override
    public PatiCheck update_itemState(PatiCheck patiCheck) {
        this.patiCheckDao.update_itemState(patiCheck);
        return this.queryByPId(patiCheck.getClistId()).get(0);
    }
}
