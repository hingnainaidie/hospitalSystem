package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.Reg_record;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Medicalrecord;
import com.cqu.hospitalsystem.service.MedicalrecordService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * (Medicalrecord)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:00:15
 */
@RestController
@RequestMapping("medicalrecord")
public class MedicalrecordController {
    /**
     * 服务对象
     */
    @Resource
    private MedicalrecordService medicalrecordService;

    @PostMapping("saveRecord")
    public DataResult saveRecord(@RequestBody Map<String,Object> param) throws ParseException {
        long regId=Integer.parseInt(param.get("regId").toString());
        String recordType=param.get("recordType").toString();
        String recordResult=param.get("recordResult").toString();
        String allergy=param.get("allergy").toString();
        String presentIllness=param.get("presentIllness").toString();
        String ill=param.get("illTime").toString();
        System.out.println(ill);

        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date illTime = fmt.parse(ill);
        System.out.println(illTime);

        Medicalrecord medicalrecord=new Medicalrecord(recordType, illTime, recordResult, allergy, presentIllness);
        Reg_record reg_record=new Reg_record();
        reg_record.setMedicalrecord(medicalrecord);
        reg_record.setRegId(regId);

        System.out.println("saveRecord");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.medicalrecordService.saveRecord(reg_record));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }
    /**
     * 分页查询
     *
     * @param medicalrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Medicalrecord>> queryByPage(Medicalrecord medicalrecord, PageRequest pageRequest) {
        return ResponseEntity.ok(this.medicalrecordService.queryByPage(medicalrecord, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Medicalrecord> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.medicalrecordService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param medicalrecord 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Medicalrecord> add(Medicalrecord medicalrecord) {
        return ResponseEntity.ok(this.medicalrecordService.insert(medicalrecord));
    }

    /**
     * 编辑数据
     *
     * @param medicalrecord 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Medicalrecord> edit(Medicalrecord medicalrecord) {
        return ResponseEntity.ok(this.medicalrecordService.update(medicalrecord));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.medicalrecordService.deleteById(id));
    }

}

