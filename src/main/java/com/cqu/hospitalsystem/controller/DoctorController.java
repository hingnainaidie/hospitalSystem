package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Office;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.service.DoctorService;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//导入封装返回类
import com.cqu.hospitalsystem.utils.result.DataResult;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Doctor)表控制层
 *
 * @author makejava
 * @since 2021-08-28 16:59:22
 */
@RestController
@RequestMapping("doctor")
public class DoctorController {
    /**
     * 服务对象
     */
    @Resource
    private DoctorService doctorService;
    @PostMapping("loginDoctor")
    public DataResult loginDoctor(@RequestBody Doctor doctor){
        System.out.println("loginDoctor：");
        System.out.println("这是前端传回的信息：");
        System.out.println(doctor.getDocPhone());
        System.out.println(doctor.getDocPassword());
        Doctor loginDoctor = this.doctorService.loginDoctor(doctor);
        System.out.println("这是后端返回的信息：");
        System.out.println(loginDoctor);
        if(loginDoctor != null){
            DataResult dataResult=DataResult.successByData(loginDoctor);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }
    @PostMapping("docInfo")
    public DataResult docInfo(@RequestBody Doctor doctor){
        System.out.println("docInfo");
        System.out.println(doctor.getDocId());
        Doctor doctor1=this.doctorService.queryById(doctor.getDocId());
        if(doctor1 != null){
            DataResult dataResult=DataResult.successByData(doctor1);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    @PostMapping("editDocPwd")
    public DataResult editDocPwd(@RequestBody Doctor doctor){
        System.out.println("editDocPwd");
        System.out.println(doctor.getDocId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.doctorService.editDocPwd(doctor));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);

    }

    @PostMapping("editDocInfo")
    public DataResult editDocInfo(@RequestBody Doctor doctor){
        System.out.println("editDocInfo");
        System.out.println(doctor.getDocId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.doctorService.editDocInfo(doctor));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }

    @PostMapping("searchAvailable")
    public DataResult searchAvailable(@RequestBody Map<String,Object> param) throws ParseException {
        System.out.println("searchAvailable");
        long officeId=Integer.parseInt(param.get("officeId").toString());
        String appoint=param.get("appointTime").toString();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date appointTime = fmt.parse(appoint);

        List<Doctor> doctorList=this.doctorService.searchAvailable(officeId,appointTime);
        JSONObject jsonObject = new JSONObject();
        if(doctorList != null){
            jsonObject.put("doctorList",doctorList);
            DataResult dataResult=DataResult.successByData(jsonObject);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    /**
     * 分页查询
     *
     * @param doctor 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Doctor>> queryByPage(Doctor doctor, PageRequest pageRequest) {
        return ResponseEntity.ok(this.doctorService.queryByPage(doctor, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Doctor> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.doctorService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param doctor 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Doctor> add(Doctor doctor) {
        return ResponseEntity.ok(this.doctorService.insert(doctor));
    }

    /**
     * 编辑数据
     *
     * @param doctor 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Doctor> edit(Doctor doctor) {
        return ResponseEntity.ok(this.doctorService.update(doctor));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.doctorService.deleteById(id));
    }


    /**
     * 示例接口！！！
     * @param doctor
     * @return
     */
    @PostMapping("queryById")
    //根据id返回医生信息
    public DataResult queryById(@RequestBody Doctor doctor){
        System.out.println(doctor.getDocId());
        Doctor doctor1=this.doctorService.queryById(doctor.getDocId());
        //使用DataResult是因为它可以额外设置状态码，也可以直接return DataResult.successByData(doctor1);
        DataResult dataResult=DataResult.successByData(doctor1);
        dataResult.setcode(666);
        return dataResult;
    }

    @PostMapping("searchDocList")
    public DataResult searchDocList(@RequestBody Office office){
        return DataResult.successByDataArray(doctorService.searchDocList(office.getOfficeName()));
    }

    @PostMapping("docDetail")
    public DataResult docDetail(@RequestBody Doctor doctor){
        return DataResult.successByData(doctorService.docDetail(doctor.getDocId()));
    }

}

