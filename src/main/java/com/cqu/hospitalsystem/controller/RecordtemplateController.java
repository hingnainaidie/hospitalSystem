package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Recordtemplate;
import com.cqu.hospitalsystem.entity.Registration;
import com.cqu.hospitalsystem.service.RecordtemplateService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Recordtemplate)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:02:04
 */
@RestController
@RequestMapping("recordtemplate")
public class RecordtemplateController {
    /**
     * 服务对象
     */
    @Resource
    private RecordtemplateService recordtemplateService;
    @PostMapping("searchTemplate")
    public DataResult searchTemplate(@RequestBody Recordtemplate recordtemplate){
        System.out.println("------------这是前端传回------------------------");
        System.out.println(recordtemplate.getTemplateName());
        Recordtemplate res = this.recordtemplateService.searchTemplate(recordtemplate);
        if(res != null){
            return DataResult.successByData(res);
        }else{
            System.out.println("出错了！！");
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    @PostMapping("saveTemplate")
    public DataResult saveTemplate(@RequestBody Recordtemplate recordtemplate){
        System.out.println("saveTemplate");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.recordtemplateService.saveTemplate(recordtemplate));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);

    }

    /**
     * 分页查询
     *
     * @param recordtemplate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Recordtemplate>> queryByPage(Recordtemplate recordtemplate, PageRequest pageRequest) {
        return ResponseEntity.ok(this.recordtemplateService.queryByPage(recordtemplate, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Recordtemplate> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.recordtemplateService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param recordtemplate 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Recordtemplate> add(Recordtemplate recordtemplate) {
        return ResponseEntity.ok(this.recordtemplateService.insert(recordtemplate));
    }


    /**
     * 编辑数据
     *
     * @param recordtemplate 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Recordtemplate> edit(Recordtemplate recordtemplate) {
        return ResponseEntity.ok(this.recordtemplateService.update(recordtemplate));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.recordtemplateService.deleteById(id));
    }

}

