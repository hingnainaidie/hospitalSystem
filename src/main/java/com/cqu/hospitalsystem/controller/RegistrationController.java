package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.DocReg;
import com.cqu.hospitalsystem.Vo.historyRegVo;
import com.cqu.hospitalsystem.Vo.undoUnpaidVo;
import com.cqu.hospitalsystem.Vo.undoVo;
import com.cqu.hospitalsystem.dao.DoctorDao;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.IdRelation;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.service.RegistrationService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (Registration)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:02:19
 */
@RestController
@RequestMapping("registration")
public class RegistrationController {
    /**
     * 服务对象
     */
    @Resource
    private RegistrationService registrationService;

    @Resource
    private RegistrationDao registrationDao;
    @Resource
    private DoctorDao doctorDao;

    /**
     * 分页查询
     *
     * @param registration 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Registration>> queryByPage(Registration registration, PageRequest pageRequest) {
        return ResponseEntity.ok(this.registrationService.queryByPage(registration, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Registration> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.registrationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param registration 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Registration> add(Registration registration) {
        return ResponseEntity.ok(this.registrationService.insert(registration));
    }

    /**
     * 编辑数据
     *
     * @param registration 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Registration> edit(Registration registration) {
        return ResponseEntity.ok(this.registrationService.update(registration));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.registrationService.deleteById(id));
    }

    /**
     * 历史预约列表
     * @param patient
     * @return
     */
    @PostMapping("showHistory")
    public DataResult showHistory(@RequestBody Patient patient){
        System.out.println("这是病人号");
        System.out.println(patient.getPatientId());
        System.out.println("这是具体数据");
        List<historyRegVo> zww=this.registrationService.showHistory(patient.getPatientId());
        for(historyRegVo item : zww){
            System.out.println(item);
        }
        return DataResult.successByDataArray(this.registrationService.showHistory(patient.getPatientId()));
    }

    @PostMapping("historyDetail")
    public DataResult historyDetail(@RequestBody IdRelation idRelation){
        System.out.println("这是哪一个单号详情");
        System.out.println(idRelation.getIdRelationId());
        historyRegVo zww = this.registrationService.historyDetail(idRelation.getIdRelationId());
        System.out.println(zww);
        return DataResult.successByData(this.registrationService.historyDetail(idRelation.getIdRelationId()));
    }

    /**
     * 未完成预约列表
     * @return
     */
    @PostMapping("showUndoList")
    public DataResult showUndoDetail(@RequestBody Patient patient){
        System.out.println("这是病人号");
        System.out.println(patient.getPatientId());
        List<undoVo> zww=this.registrationService.showUndoList(patient.getPatientId());
        for(undoVo item : zww){
            System.out.println(item);
        }
        return DataResult.successByDataArray(this.registrationService.showUndoList(patient.getPatientId()));
    }

    /**
     * 未完成预约详情
     * @param registration
     * @return
     */
    @PostMapping("showUndoDetail")
    public DataResult showUndoDetail(@RequestBody Registration registration){
        undoVo zww = this.registrationService.showUndoDetail(registration.getRegId());
        System.out.println("未完成详情");
        System.out.println(zww);
        return DataResult.successByData(zww);
    }

    /**
     * 就诊中未缴费详情
     * @param registration
     * @return
     */
    @PostMapping("showUnpaidDetail")
    public DataResult showUnpaidDetail(@RequestBody Registration registration){
        undoUnpaidVo zww = this.registrationService.showUnpaidDetail(registration.getRegId());
        System.out.println("就诊中未缴费详情");
        System.out.println(zww);
        return DataResult.successByData(zww);
    }
    /**
     * 支付挂号单
     * @param registration
     * @return
     */
    @PostMapping("payOrder")
    public DataResult payOrder(@RequestBody Registration registration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.registrationService.payOrder(registration.getRegId()));
        return DataResult.successByData(jsonObject);
    }

    /**
     * 取消挂号
     * @param registration
     * @return
     */
    @PostMapping("cancelOrder")
    public DataResult cancelOrder(@RequestBody Registration registration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.registrationService.cancelOrder(registration.getRegId()));
        return DataResult.successByData(jsonObject);
    }

    /**
     * 新增数据
     * @param param
     * @return
     * @throws ParseException
     */
    @PostMapping("doOrder")
    public DataResult doOrder(@RequestBody Map<String,Object> param) throws ParseException {
        Long doc_id=new Long(Integer.parseInt(param.get("doctorId").toString()));
        Long patient_id=new Long(Integer.parseInt(param.get("patientId").toString()));
        String appointmentTime=param.get("appointmentTime").toString();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date prescriptionTime = fmt.parse(appointmentTime);
        Doctor doctor=doctorDao.queryById(doc_id);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(prescriptionTime);
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        Date tomorrow = calendar.getTime();
        int count=registrationDao.countAppoint(doc_id,prescriptionTime,tomorrow);
        System.out.println("当前医生最大人数:"+doctor.getMaxPatient());
        System.out.println("count:"+count);
        DataResult dataResult=new DataResult();
        if(count>=doctor.getMaxPatient()){
            dataResult.setcode(701);
            return dataResult;
        }
        Registration registration=new Registration();
        registration.setDocId(doc_id);
        registration.setPatientId(patient_id);
        registration.setState(0);
        registration.setAppointmentTime(prescriptionTime);
        registration.setQueueNumber(0L);
        Date date = new Date(System.currentTimeMillis());
        registration.setRegTime(date);
        System.out.println(registration.getDocId());
        dataResult.setDatas(this.registrationService.insert(registration));
        dataResult.setcode(666);
        return dataResult;
    }

    @PostMapping("endVisit")
    public DataResult endVisit(@RequestBody Registration registration){
        System.out.println("这是前端传回的信息：");
        System.out.println(registration.getRegId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.registrationService.endVisit(registration));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }
    @PostMapping("suspendVisit")
    public DataResult suspendVisit(@RequestBody Registration registration){
        System.out.println("这是前端传回的信息：");
        System.out.println(registration.getRegId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.registrationService.suspendVisit(registration));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }

    @PostMapping("searchReg")
    public DataResult searchReg(@RequestBody Patient patient){
        System.out.println("searchReg");
        System.out.println(patient.getPatientIdentify());
        List<DocReg> docRegList=this.registrationService.searchReg(patient);
        JSONObject jsonObject = new JSONObject();
        if(docRegList != null){
            jsonObject.put("docRegList",docRegList);
            DataResult dataResult=DataResult.successByData(jsonObject);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }
    @PostMapping("visiting")
    public DataResult visiting(@RequestBody Doctor doctor){
        System.out.println(doctor.getDocId());
        JSONObject jsonObject = new JSONObject();
        Registration r=this.registrationService.visiting(doctor);
        if(r != null){
            List<Patient> list=this.registrationService.searchPatient(r);
            jsonObject.put("patientList",list);
            jsonObject.put("regId",r.getRegId());
            DataResult dataResult=DataResult.successByData(jsonObject);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }

    }


}

