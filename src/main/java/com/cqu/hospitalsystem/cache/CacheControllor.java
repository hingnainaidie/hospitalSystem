package com.cqu.hospitalsystem.cache;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.service.RegistrationService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Map;

@RestController
@RequestMapping("cache")
public class CacheControllor {
    @Resource
    private CacheService cacheService;
    //前台缴费，入队末尾
    @PostMapping("push")
    public DataResult push(@RequestBody Map<String,Object> param){
        Long reg_id=new Long(Integer.parseInt(param.get("regId").toString()));
        return DataResult.successByDataArray(cacheService.push(reg_id));
    }
    //预约缴费，入队3-10
    @PostMapping("payOrder")
    public DataResult pushInsert(@RequestBody Map<String,Object> param){
        Long reg_id=new Long(Integer.parseInt(param.get("regId").toString()));
        return DataResult.successByDataArray(cacheService.pushInsert(reg_id));
    }
    //叫号：出队，返回新的队列
    @PostMapping("poll")
    public DataResult poll(@RequestBody Map<String,Object> param){
        Long doc_id=new Long(Integer.parseInt(param.get("docId").toString()));
        return DataResult.successByDataArray(cacheService.poll(doc_id));
    }
    //患者查看某个预约的排队号
    @PostMapping("checkQueInfo")
    public DataResult patientCheck(@RequestBody Map<String,Object> param){
        Long patient_id=new Long(Integer.parseInt(param.get("patientId").toString()));
        JSONObject jsonObject=new JSONObject();
        int queue_number=cacheService.getQueueNum(patient_id);
        Long reg_id=cacheService.getSeqReg_id(patient_id);
        jsonObject.put("queue_number",queue_number);
        jsonObject.put("reg_id",reg_id);
        DataResult dataResult=DataResult.successByData(jsonObject);
        if(queue_number==0){
            dataResult.setcode(700);
        }else{
            dataResult.setcode(666);
        }
        return dataResult;
    }
    //医生查看所有排队信息
    @PostMapping("startVisit")//查看所有排队信息
    public DataResult docRegQueue(@RequestBody Doctor doctor){
        return DataResult.successByDataArray(cacheService.getQueue(doctor.getDocId()));
    }

    @PostMapping("unhang")//解挂，插入到前5的位置
    public DataResult unhang(@RequestBody Map<String,Object> param){
        Long reg_id=new Long(Integer.parseInt(param.get("regId").toString()));
        return DataResult.successByDataArray(cacheService.unhang(reg_id));
    }

    //取消挂号的预约
}
