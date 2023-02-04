package com.cqu.hospitalsystem.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.PatiCheck;
import com.cqu.hospitalsystem.entity.PatiPresRelation;
import com.cqu.hospitalsystem.service.PatiCheckService;
import com.cqu.hospitalsystem.service.PatiPresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("patiCheck")
public class PatiCheckController {
    @Resource
    private PatiCheckService patiCheckService;
    @PostMapping("getPatiCheckAll")
    public com.cqu.hospitalsystem.utils.result.DataResult getPatiCheckAll(){

        List<PatiCheck> patiPreslist=this.patiCheckService.queryAll();
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
    @PostMapping("getCheckDetails")
    public com.cqu.hospitalsystem.utils.result.DataResult getCheckDetails(@RequestBody PatiCheck patiCheck){
        //System.out.println(patiCheck.getClistId());
        List<PatiCheck> patiPreslist=this.patiCheckService.queryByPId(patiCheck.getClistId());
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
    public com.cqu.hospitalsystem.utils.result.DataResult searchPatipres(@RequestBody PatiCheck patiCheck){
        //System.out.println(patiCheck.getPatientIdentify());
        List<PatiCheck> patiCheck1=this.patiCheckService.queryById(patiCheck.getPatientIdentify());
        String result = JSON.toJSONString(patiCheck1);
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

    @RequestMapping("updatelistState")
    public ResponseEntity<PatiCheck> updatelistState(@RequestBody PatiCheck patiCheck) {
        System.out.println(patiCheck.getClistId());
        System.out.println(patiCheck.getCliststate());
        return ResponseEntity.ok(this.patiCheckService.update(patiCheck));
    }
    @RequestMapping("updatecitemState")
    public ResponseEntity<PatiCheck> updatecitemState(@RequestBody PatiCheck patiCheck) {
        System.out.println(patiCheck.getItemListId());
        System.out.println(patiCheck.getCitemstate());
        return ResponseEntity.ok(this.patiCheckService.update_itemState(patiCheck));
    }
    @RequestMapping("returnCheck")
    public ResponseEntity<PatiCheck> returnCheck(@RequestBody PatiCheck patiCheck) {
        System.out.println(patiCheck.getItemListId());
        System.out.println(patiCheck.getClistId());
        return ResponseEntity.ok(this.patiCheckService.update_checkState(patiCheck));
    }

}
