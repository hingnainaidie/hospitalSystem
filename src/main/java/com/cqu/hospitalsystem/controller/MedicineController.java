package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.entity.Doctor;
import com.cqu.hospitalsystem.entity.Medicine;
import com.cqu.hospitalsystem.service.MedicineService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Medicine)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:00:37
 */
@RestController
@RequestMapping("medicine")
public class MedicineController {
    /**
     * 服务对象
     */
    @Resource
    private MedicineService medicineService;
    @PostMapping("allMedicine")
    public DataResult allMedicine(){
        List<Medicine> list=this.medicineService.allMedicine();
        JSONObject jsonObject = new JSONObject();
        if(list != null){
            jsonObject.put("medicineList",list);
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
     * @param medicine 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Medicine>> queryByPage(Medicine medicine, PageRequest pageRequest) {
        return ResponseEntity.ok(this.medicineService.queryByPage(medicine, pageRequest));
    }
    @PostMapping("chooseMedicine")
    public DataResult chooseMedicine(@RequestBody Medicine medicine){
        System.out.println("chooseMedicine");
        System.out.println(medicine.getMedicineId());
        Medicine medicine1=this.medicineService.queryById(medicine.getMedicineId());
        if(medicine1 != null){
            DataResult dataResult=DataResult.successByData(medicine1);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Medicine> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.medicineService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param medicine 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Medicine> add(Medicine medicine) {
        return ResponseEntity.ok(this.medicineService.insert(medicine));
    }

    /**
     * 编辑数据
     *
     * @param medicine 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Medicine> edit(Medicine medicine) {
        return ResponseEntity.ok(this.medicineService.update(medicine));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.medicineService.deleteById(id));
    }

    /**
     * 退药
     */
    @PostMapping("cancelMedicine")
    public DataResult cancelMedicine(@RequestBody preMedicineVo preMedicineVo){
        System.out.println("这是要取消的药品");
        System.out.println("取消处方单ID："+preMedicineVo.getPrescriptionId());
        System.out.println("这是药品ID："+preMedicineVo.getMedicineId());
        System.out.println(preMedicineVo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.medicineService.cancelMedicine(preMedicineVo));
        return DataResult.successByData(jsonObject);
    }

}

