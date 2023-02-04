package com.cqu.hospitalsystem.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.service.DoctorService;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import com.cqu.hospitalsystem.service.PatiPresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("patiPres")
public class PatiPresController {
    @Resource
    private PatiPresService patiPresService;
    /**
     * 示例接口！！！
     * @return
     */
    @PostMapping("getPatipresAll")
    /*public ResponseEntity<Page<PatiPresRelation>> queryByPage(PatiPresRelation patiPresRelation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.patiPresService.queryByPage(patiPresRelation, pageRequest));
    }*/
    public com.cqu.hospitalsystem.utils.result.DataResult getPatipresAll(){

        List<PatiPresRelation> patiPreslist=this.patiPresService.queryAll();
        String result = JSON.toJSONString(patiPreslist);
        //System.out.println(result);
        //result=result.replace("[", "{");
        //result=result.replace("]", "}");
        String jsStr="{\"data\":"+result+"}";

        //System.out.println(jsStr);
        JSONObject js=JSONObject.parseObject(jsStr);

        //JSONArray jsonArray = JSONArray.parseArray(result);
        //PatiPresRelation patiPreslist1=patiPreslist.get(0);
        //使用DataResult是因为它可以额外设置状态码，也可以直接return DataResult.successByData(doctor1);
        com.cqu.hospitalsystem.utils.result.DataResult dataResult= com.cqu.hospitalsystem.utils.result.DataResult.successByData(js);
        dataResult.setcode(777);
        return dataResult;
    }
    @PostMapping("getMediDetails")
    public com.cqu.hospitalsystem.utils.result.DataResult getMediDetails(@RequestBody PatiPresRelation patiPresRelation){
        System.out.println(patiPresRelation.getPrescriptionId());
        List<PatiPresRelation> patiPreslist=this.patiPresService.queryByPId(patiPresRelation.getPrescriptionId());
        String result = JSON.toJSONString(patiPreslist);
        //System.out.println(result);
        //result=result.replace("[", "{");
        //result=result.replace("]", "}");
        String jsStr="{\"data\":"+result+"}";

        //System.out.println(jsStr);
        JSONObject js=JSONObject.parseObject(jsStr);
        //使用DataResult是因为它可以额外设置状态码，也可以直接return DataResult.successByData(doctor1);
        com.cqu.hospitalsystem.utils.result.DataResult dataResult= com.cqu.hospitalsystem.utils.result.DataResult.successByData(js);
        dataResult.setcode(777);
        return dataResult;
    }

    @PostMapping("searchPatipres")
    public com.cqu.hospitalsystem.utils.result.DataResult searchPatipres(@RequestBody PatiPresRelation patiPresRelation){
        //System.out.println(patiPresRelation.getPatientIdentify());
        List<PatiPresRelation> patiPresRelation1=this.patiPresService.queryById(patiPresRelation.getPatientIdentify());
        //使用DataResult是因为它可以额外设置状态码，也可以直接return DataResult.successByData(doctor1);
        String result = JSON.toJSONString(patiPresRelation1);
        //System.out.println(result);
        //result=result.replace("[", "{");
        //result=result.replace("]", "}");
        String jsStr="{\"data\":"+result+"}";

        //System.out.println(jsStr);
        JSONObject js=JSONObject.parseObject(jsStr);
        com.cqu.hospitalsystem.utils.result.DataResult dataResult= com.cqu.hospitalsystem.utils.result.DataResult.successByData(js);
        dataResult.setcode(777);
        return dataResult;
    }

    @RequestMapping("updatepreState")
    public ResponseEntity<PatiPresRelation> updatepreState(@RequestBody PatiPresRelation patiPresRelation) {
        //System.out.println(patiPresRelation.getPrescriptionId());
        //System.out.println(patiPresRelation.getPrestate());
        return ResponseEntity.ok(this.patiPresService.update(patiPresRelation));
    }

    @RequestMapping("returnMedicine")
    public ResponseEntity<PatiPresRelation> returnMedicine(@RequestBody PatiPresRelation patiPresRelation) {
        System.out.println(patiPresRelation.getConnectId());
        System.out.println(patiPresRelation.getPrescriptionId());
        return ResponseEntity.ok(this.patiPresService.update_mediState(patiPresRelation));
    }
    @PostMapping("showreturnSys")
    public com.cqu.hospitalsystem.utils.result.DataResult showreturnSys(){

        List<PatiPresRelation> patiPreslist=this.patiPresService.queryreturnAll();
        String result = JSON.toJSONString(patiPreslist);
        //System.out.println(result);
        //result=result.replace("[", "{");
        //result=result.replace("]", "}");
        String jsStr="{\"data\":"+result+"}";

        //System.out.println(jsStr);
        JSONObject js=JSONObject.parseObject(jsStr);

        //JSONArray jsonArray = JSONArray.parseArray(result);
        //PatiPresRelation patiPreslist1=patiPreslist.get(0);
        //使用DataResult是因为它可以额外设置状态码，也可以直接return DataResult.successByData(doctor1);
        com.cqu.hospitalsystem.utils.result.DataResult dataResult= com.cqu.hospitalsystem.utils.result.DataResult.successByData(js);
        dataResult.setcode(777);
        return dataResult;
    }

}