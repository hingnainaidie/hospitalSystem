package com.cqu.hospitalsystem.service.impl;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import com.cqu.hospitalsystem.dao.PatiPresDao;
import com.cqu.hospitalsystem.service.PatiPresService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import javax.annotation.Resource;
import java.util.List;

@Service("PatiPresService")
public class PatiPresServiceImpl implements PatiPresService{
    @Resource
    private PatiPresDao patiPresDao;
    /**
     * 分页查询
     *
     * @param patiPresRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<PatiPresRelation> queryByPage(PatiPresRelation patiPresRelation, PageRequest pageRequest) {
        long total = this.patiPresDao.count(patiPresRelation);
        return new PageImpl<>(this.patiPresDao.queryAllByLimit(patiPresRelation, pageRequest), pageRequest, total);
    }
    @Override
    public List<PatiPresRelation> queryAll(){
        return this.patiPresDao.queryAll();
    }
    @Override
    public List<PatiPresRelation> queryreturnAll(){
        return this.patiPresDao.queryreturnAll();
    }

    public List<PatiPresRelation> queryById(String patientIdentify) {
        return this.patiPresDao.queryById(patientIdentify);
    }

    @Override
    public List<PatiPresRelation> queryByPId(Long prescriptionId){
        return this.patiPresDao.queryByPId(prescriptionId);
    }
    @Override
    public PatiPresRelation update(PatiPresRelation patiPresRelation) {
        this.patiPresDao.update(patiPresRelation);
        return this.queryByPId(patiPresRelation.getPrescriptionId()).get(0);
    }

    @Override
    public PatiPresRelation update_mediState(PatiPresRelation patiPresRelation){
        this.patiPresDao.update_mediState(patiPresRelation);
        return this.queryByPId(patiPresRelation.getPrescriptionId()).get(0);
    }
}
