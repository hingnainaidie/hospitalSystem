package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checkitem;
import com.cqu.hospitalsystem.entity.Medicine;
import com.cqu.hospitalsystem.service.CheckitemService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import com.cqu.hospitalsystem.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Checkitem)表控制层
 *
 * @author makejava
 * @since 2021-08-28 16:58:18
 */
@RestController
@RequestMapping("checkitem")
public class CheckitemController {
    /**
     * 服务对象
     */
    @Resource
    private CheckitemService checkitemService;

    @PostMapping("cancelCheck")
    public DataResult cancelCheck(@RequestBody preCheck preCheck){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess",this.checkitemService.cancelCheck(preCheck));
        return DataResult.successByData(jsonObject);
    }

    @PostMapping("allCheckitem")
    public DataResult allCheckitem(){
        List<Checkitem> list=this.checkitemService.allCheckitem();
        JSONObject jsonObject = new JSONObject();
        if(list != null){
            jsonObject.put("checkitemList",list);
            DataResult dataResult=DataResult.successByData(jsonObject);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }

    @PostMapping("chooseCheckitem")
    public DataResult chooseCheckitem(@RequestBody Checkitem checkitem){
        System.out.println("chooseCheckitem");
        System.out.println(checkitem.getCheckitemId());
        Checkitem c=this.checkitemService.queryById(checkitem.getCheckitemId());
        if(c != null){
            DataResult dataResult=DataResult.successByData(c);
            dataResult.setcode(666);
            return dataResult;
        }else{
            return DataResult.errByErrCode(Code.ACCOUNT_ERROR);
        }
    }
    /**
     * 分页查询
     *
     * @param checkitem 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Checkitem>> queryByPage(Checkitem checkitem, PageRequest pageRequest) {
        return ResponseEntity.ok(this.checkitemService.queryByPage(checkitem, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Checkitem> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.checkitemService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param checkitem 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Checkitem> add(Checkitem checkitem) {
        return ResponseEntity.ok(this.checkitemService.insert(checkitem));
    }

    /**
     * 编辑数据
     *
     * @param checkitem 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Checkitem> edit(Checkitem checkitem) {
        return ResponseEntity.ok(this.checkitemService.update(checkitem));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.checkitemService.deleteById(id));
    }


}

