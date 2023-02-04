package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.Reg_record;
import com.cqu.hospitalsystem.dao.IdRelationDao;
import com.cqu.hospitalsystem.entity.Medicalrecord;
import com.cqu.hospitalsystem.dao.MedicalrecordDao;
import com.cqu.hospitalsystem.service.MedicalrecordService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Medicalrecord)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:00:15
 */
@Service("medicalrecordService")
public class MedicalrecordServiceImpl implements MedicalrecordService {
    @Resource
    private MedicalrecordDao medicalrecordDao;
    @Resource
    private IdRelationDao idRelationDao;
    @Override
    public boolean saveRecord(Reg_record reg_record){

        int isInserted=this.medicalrecordDao.insert(reg_record.getMedicalrecord());
        if(isInserted!=0){ //插入成功
            Long mrecordId=this.medicalrecordDao.getMedicalID();
            if (mrecordId!=null){
                if(this.idRelationDao.findbyregId(reg_record.getRegId())==null){ //没有记录
                    return this.idRelationDao.insert_idrelation(reg_record.getRegId(),mrecordId);
                }
                else{ //有记录
                    return this.idRelationDao.update_mrecordId(reg_record.getRegId(),mrecordId);
                }

            }
            else{
                System.out.println("关联表没有插入");
                return false;
            }
        }
        else{
            System.out.println("病历没有插入");
            return false;
        }
    }


    /**
     * 通过ID查询单条数据
     *
     * @param mrecordId 主键
     * @return 实例对象
     */
    @Override
    public Medicalrecord queryById(Long mrecordId) {
        return this.medicalrecordDao.queryById(mrecordId);
    }

    /**
     * 分页查询
     *
     * @param medicalrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Medicalrecord> queryByPage(Medicalrecord medicalrecord, PageRequest pageRequest) {
        long total = this.medicalrecordDao.count(medicalrecord);
        return new PageImpl<>(this.medicalrecordDao.queryAllByLimit(medicalrecord, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param medicalrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Medicalrecord insert(Medicalrecord medicalrecord) {
        this.medicalrecordDao.insert(medicalrecord);
        return medicalrecord;
    }

    /**
     * 修改数据
     *
     * @param medicalrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Medicalrecord update(Medicalrecord medicalrecord) {
        this.medicalrecordDao.update(medicalrecord);
        return this.queryById(medicalrecord.getMrecordId());
    }

    /**
     * 通过主键删除数据
     *
     * @param mrecordId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long mrecordId) {
        return this.medicalrecordDao.deleteById(mrecordId) > 0;
    }
}
