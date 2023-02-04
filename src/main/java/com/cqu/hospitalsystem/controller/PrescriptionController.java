package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.*;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Prescription;
import com.cqu.hospitalsystem.service.PrescriptionService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * (Prescription)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:01:48
 */
@RestController
@RequestMapping("prescription")
public class PrescriptionController {
    /**
     * 服务对象
     */
    @Resource
    private PrescriptionService prescriptionService;

    /**
     * 分页查询
     *
     * @param prescription 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Prescription>> queryByPage(Prescription prescription, PageRequest pageRequest) {
        return ResponseEntity.ok(this.prescriptionService.queryByPage(prescription, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Prescription> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.prescriptionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param prescription 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Prescription> add(Prescription prescription) {
        return ResponseEntity.ok(this.prescriptionService.insert(prescription));
    }

    /**
     * 编辑数据
     *
     * @param prescription 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Prescription> edit(Prescription prescription) {
        return ResponseEntity.ok(this.prescriptionService.update(prescription));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.prescriptionService.deleteById(id));
    }

    /**
     * 显示已缴费处方单药品列表 ！！！
     * @param patient
     * @return
     */
    @PostMapping("showPaidMedicine")
    public DataResult showPaidMedicine(@RequestBody Patient patient){
        System.out.println("这是已缴费处方单列表");
        System.out.println(patient);
        //获取当天日期
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        System.out.println("当前日期：" + dateNowStr);

        Date appointmentTime= new Date(); //预约时间
        String apTime = "";
        Double totalPrice = 0.0;
        List<preMedicineVo> zww= (List<preMedicineVo>) this.prescriptionService.showPaidMedicine(patient);
        for (preMedicineVo item : zww){
            //只把未退费的总价相加，3为已退费
            if(item.getState()!=3){
                totalPrice = totalPrice + item.getPricePer()*item.getAmount();
                item.setTotalPrice(totalPrice);
            }
        }
        if( zww.size()!=0 ){
            zww.get(0).setTotalPrice(totalPrice);
            appointmentTime = zww.get(0).getAppointmentTime();
            apTime = sdf.format(appointmentTime);
            System.out.println("预约时间："+apTime);
            //只显示与当前日期一样的处方单
            if(apTime.equals(dateNowStr) ){
                System.out.println("我进来了！！！处方单");
                return DataResult.successByDataArray(zww);
            }else {
                System.out.println("处方单空！");
                //当天无预约，返回空
                return DataResult.successByData(null);
            }
        }
        //当天无预约，返回空
        return DataResult.successByData(null);

    }

    /**
     * 历史处方单药品列表
     * @param prescription
     * @return
     */
    @PostMapping("showHistoryMedicine")
    public DataResult showHistoryMedicine(@RequestBody Prescription prescription){
        System.out.println("这是历史药品列表");
        List<preMedicineVo> zww= this.prescriptionService.showHistoryMedicine(prescription.getPrescriptionId());
        Double totalPrice = 0.0;
        for (preMedicineVo item : zww){
            //只把未退费的总价相加，3为已退费
            if(item.getState()!=3){
                totalPrice = totalPrice + item.getPricePer()*item.getAmount();
                item.setTotalPrice(totalPrice);
            }
        }
        if( zww.size()!=0 ){
            zww.get(0).setTotalPrice(totalPrice);
        }
        return DataResult.successByDataArray(zww);
    }

    /**
     * 未缴费处方单
     * @param prescription
     * @return
     */
    @PostMapping("showUnpaidPre")
    public DataResult showUnpaidPre(@RequestBody Prescription prescription){
        System.out.println("这是历史药品列表");
        List<preMedicineVo> zww= this.prescriptionService.showUnpaidPre(prescription.getPrescriptionId());
        Double totalPrice = 0.0;
        for (preMedicineVo item : zww){
            totalPrice = totalPrice + item.getPricePer()*item.getAmount();
            item.setTotalPrice(totalPrice);
            System.out.println("这是未缴费的处方单列表");
            System.out.println(item);
        }
        if( zww.size()!=0 ){
            zww.get(0).setTotalPrice(totalPrice);
        }
        return DataResult.successByDataArray(zww);
    }

    /**
     * 交处方单费用
     * @param prescription
     * @return
     */
    @PostMapping("payMedicine")
    public DataResult payMedicine(@RequestBody Prescription prescription){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.prescriptionService.payMedicine(prescription.getPrescriptionId()));
        return DataResult.successByData(jsonObject);
    }


    /**
     * 查看处方单
     * @param prescription
     * @return
     */
    @PostMapping("showMedicine")
    public DataResult showMedicine(@RequestBody Prescription prescription){
        System.out.println("查看处方单");
        System.out.println(prescription.getPrescriptionId());
        prescriptionVo prescription1 = this.prescriptionService.showMedicine(prescription.getPrescriptionId());
        System.out.println(prescription1);
        return DataResult.successByData(this.prescriptionService.showMedicine(prescription.getPrescriptionId()));
    }
    @PostMapping("savePrescription")
    public DataResult savePrescription(@RequestBody MediPresNew mediPresNew) throws ParseException {
        List<MedicineAmount> MAlist=mediPresNew.getMedicineList();

        List<Long> medicineList=new LinkedList<>();
        List<Integer> amountList=new LinkedList<>();
        String prescriptionTime=mediPresNew.getPrescriptionTime();
        Long regId=mediPresNew.getRegId();
        for(MedicineAmount m:MAlist){
            medicineList.add(m.getMedicineId());
            amountList.add(m.getNum());
        }

        MediPres medipres=new MediPres();
        medipres.setPrescriptionTime(prescriptionTime);
        medipres.setMedicineList(medicineList);
        medipres.setRegId(regId);
        medipres.setAmountList(amountList);

        System.out.println("savePrescription");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.prescriptionService.savePrescription(medipres));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }

}

