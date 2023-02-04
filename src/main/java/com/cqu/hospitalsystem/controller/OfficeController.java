package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.entity.Office;
import com.cqu.hospitalsystem.service.OfficeService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Office)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:00:56
 */
@RestController
@RequestMapping("office")
public class OfficeController {
    /**
     * 服务对象
     */
    @Resource
    private OfficeService officeService;

    @PostMapping("allOffice")
    public DataResult allOffice(){
        List<Office> res = this.officeService.allOffice();
        if(res != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("officeList", res);
            System.out.println(jsonObject);
            return DataResult.successByData(jsonObject);
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }
    /**
     * 分页查询
     *
     * @param office 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Office>> queryByPage(Office office, PageRequest pageRequest) {
        return ResponseEntity.ok(this.officeService.queryByPage(office, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Office> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.officeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param office 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Office> add(Office office) {
        return ResponseEntity.ok(this.officeService.insert(office));
    }

    /**
     * 编辑数据
     *
     * @param office 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Office> edit(Office office) {
        return ResponseEntity.ok(this.officeService.update(office));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.officeService.deleteById(id));
    }

}

