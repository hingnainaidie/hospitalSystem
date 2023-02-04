package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.service.PatientService;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cqu.hospitalsystem.utils.result.DataResult;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Patient)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:01:14
 */
@RestController
@RequestMapping("patient")
public class PatientController {
    /**
     * 服务对象
     */
    @Resource
    private PatientService patientService;

    /**
     * 分页查询
     *
     * @param patient 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Patient>> queryByPage(Patient patient, PageRequest pageRequest) {
        return ResponseEntity.ok(this.patientService.queryByPage(patient, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Patient> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.patientService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param patient 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Patient> add(Patient patient) {
        return ResponseEntity.ok(this.patientService.insert(patient));
    }

    /**
     * 编辑数据
     *
     * @param patient 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Patient> edit(Patient patient) {
        return ResponseEntity.ok(this.patientService.update(patient));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.patientService.deleteById(id));
    }

    /**
     * 患者登陆
     * @param patient
     * @return
     */
    @PostMapping("loginPatient")
    public DataResult loginPatient(@RequestBody Patient patient){
        Patient loginPatient = this.patientService.loginPatient(patient);
        if(loginPatient != null){
            return DataResult.successByData(loginPatient);
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    /**
     * 修改患者基本信息
     * @param patient
     * @return
     */
    @PostMapping(value = "editInfo")
    public DataResult editInfo(@RequestBody Patient patient){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.patientService.editInfo(patient));
        return DataResult.successByData(jsonObject);
    }

    /**
     * 修改患者密码
     * @param param
     * @return
     */
    @PostMapping(value = "editPwd")
    public DataResult editPwd(@RequestBody Map<String,Object> param){
        String patientPwd=param.get("patientPwd").toString();
        String newPwd=param.get("newPwd").toString();
        long patientId=Integer.parseInt(param.get("patientId").toString());
        JSONObject jsonObject = new JSONObject();
        Patient patient=this.patientService.searchPwd(patientId);
        System.out.println(patient.getPatientPassword());
        System.out.println(patientPwd);
        if(patient.getPatientPassword().equals(patientPwd)){
            jsonObject.put("isSuccess", this.patientService.editPwd(patientId,newPwd));
            jsonObject.put("error", "修改失败");
        }else{
            jsonObject.put("isSuccess", false);
            jsonObject.put("error", "旧密码错误");
        }
        return DataResult.successByData(jsonObject);
    }

    @PostMapping( "register")
    public DataResult register(@RequestBody Patient patient){
        Patient dataBasePatient = this.patientService.searchForPatient(patient);
        if(dataBasePatient == null){
            System.out.println(patient.toString());
            return DataResult.successByData(this.patientService.insert(patient));
        }
        else {
            return DataResult.errByErrCode(Code.ACCOUNT_REPET);
        }
    }

    @PostMapping("doctor_visit")
    public DataResult doctor_visit(@RequestBody Registration registration){
        System.out.println("------------这是前端传回------------------------");
        System.out.println(registration.getRegId());
        Patient regPatient = this.patientService.reg_patient(registration.getRegId());
        if(regPatient != null){
            System.out.println(regPatient);
            return DataResult.successByData(regPatient);
        }else{
            System.out.println("出错了！！");
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    @PostMapping("searchPatient")
    public DataResult searchPatient(@RequestBody Patient patient){
        Patient res = this.patientService.searchPatient(patient);
        if(res != null){
            return DataResult.successByData(res);
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    @PostMapping("regByReception")
    public DataResult regByReception(@RequestBody Patient patient){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.patientService.regByReception(patient));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }
}

