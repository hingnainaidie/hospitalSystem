package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.MediPres;
import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.Vo.prescriptionVo;
import com.cqu.hospitalsystem.dao.IdRelationDao;
import com.cqu.hospitalsystem.dao.PresMediDao;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Prescription;
import com.cqu.hospitalsystem.dao.PrescriptionDao;
import com.cqu.hospitalsystem.service.PrescriptionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Prescription)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:01:48
 */
@Service("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {
    @Resource
    private PrescriptionDao prescriptionDao;
    @Resource
    private IdRelationDao idRelationDao;
    @Resource
    private PresMediDao presMediDao;

    @Override
    public boolean savePrescription(MediPres medipres) throws ParseException {
        String temp=medipres.getPrescriptionTime();
        Long regId=medipres.getRegId();
        List<Long> medicineList= medipres.getMedicineList();
        List<Integer> amountList= medipres.getAmountList();

        //prescription对象
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //fmt.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Date prescriptionTime = fmt.parse(temp);

        System.out.println(temp);
        System.out.println(prescriptionTime);

        Prescription p=new Prescription();
        p.setPrescriptionTime(prescriptionTime);
        p.setPrescriptionStatement(0); //处方状态0：未交费

        int isInserted=this.prescriptionDao.insert(p);
        if(isInserted!=0){ //插入成功
            Long pId=this.prescriptionDao.getPrescriptionId(); //拿到刚插入的id
            boolean flaginsert=false;
            //1、更新或插入关联表
            if(this.idRelationDao.findbyregId(regId)==null){ //没有记录
                flaginsert=this.idRelationDao.insert_idRePres(regId,pId);
            }
            else{ //有记录
                flaginsert=this.idRelationDao.update_idRePres(regId,pId);
            }

            //2、插入pres_medi关联表
            if(flaginsert){
                int len=medicineList.size();
                for(int i=0;i<len;i++){
                    boolean flag=this.presMediDao.insertPresMedi(pId, medicineList.get(i),amountList.get(i),0);
                    if(flag) continue;
                    else{
                        System.out.println("pres_medi关联表更新失败！！");
                    }
                }
                return true;
            }
            else{
                System.out.println("id关联表没有更新！！");
                return false;
            }
        }
        else{
            System.out.println("处方没有插入！！");
            return false;
        }
    }


    /**
     * 通过ID查询单条数据
     *
     * @param prescriptionId 主键
     * @return 实例对象
     */
    @Override
    public Prescription queryById(Long prescriptionId) {
        return this.prescriptionDao.queryById(prescriptionId);
    }

    /**
     * 分页查询
     *
     * @param prescription 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Prescription> queryByPage(Prescription prescription, PageRequest pageRequest) {
        long total = this.prescriptionDao.count(prescription);
        return new PageImpl<>(this.prescriptionDao.queryAllByLimit(prescription, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param prescription 实例对象
     * @return 实例对象
     */
    @Override
    public Prescription insert(Prescription prescription) {
        this.prescriptionDao.insert(prescription);
        return prescription;
    }

    /**
     * 修改数据
     *
     * @param prescription 实例对象
     * @return 实例对象
     */
    @Override
    public Prescription update(Prescription prescription) {
        this.prescriptionDao.update(prescription);
        return this.queryById(prescription.getPrescriptionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param prescriptionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long prescriptionId) {
        return this.prescriptionDao.deleteById(prescriptionId) > 0;
    }

    /**
     * 显示已缴费处方单药品列表
     * @param patient
     * @return
     */
    @Override
    public List<preMedicineVo> showPaidMedicine(Patient patient) {
        return this.prescriptionDao.showPaidMedicine(patient);
    }

    /**
     * 查看处方单
     * @param prescriptionId
     * @return
     */
    @Override
    public prescriptionVo showMedicine(Long prescriptionId) {
        return this.prescriptionDao.showMedicine(prescriptionId);
    }

    /**
     * 历史处方单列表
     * @param prescriptionId
     * @return
     */
    @Override
    public List<preMedicineVo> showHistoryMedicine(Long prescriptionId) {
        return this.prescriptionDao.showHistoryMedicine(prescriptionId);
    }

    @Override
    public List<preMedicineVo> showUnpaidPre(Long prescriptionId) {
        return this.prescriptionDao.showUnpaidPre(prescriptionId);
    }

    @Override
    public boolean payMedicine(Long prescriptionId) {
        return this.prescriptionDao.payMedicine(prescriptionId);
    }
}
