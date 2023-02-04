package com.cqu.hospitalsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqu.hospitalsystem.Vo.Check;
import com.cqu.hospitalsystem.Vo.CheckNew;
import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checkitem;
import com.cqu.hospitalsystem.entity.Checklist;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.entity.Prescription;
import com.cqu.hospitalsystem.service.ChecklistService;
import com.cqu.hospitalsystem.utils.result.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * (Checklist)表控制层
 *
 * @author makejava
 * @since 2021-08-28 16:58:52
 */
@RestController
@RequestMapping("checklist")
public class ChecklistController {
    /**
     * 服务对象
     */
    @Resource
    private ChecklistService checklistService;


    @PostMapping("saveChecklist")
    public DataResult saveChecklist(@RequestBody CheckNew checkNew) throws ParseException {
        String prescriptionTime=checkNew.getPrescribeTime();
        Long regId=checkNew.getRegId();
        List<Checkitem> clist=checkNew.getCheckitemList();
        List<Long> citemlist=new LinkedList<>();
        for(Checkitem c:clist){
            citemlist.add(c.getCheckitemId());
        }
        Check check=new Check();
        check.setCheckitemList(citemlist);
        check.setPrescribeTime(prescriptionTime);
        check.setRegId(regId);

        System.out.println("saveChecklist");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.checklistService.saveChecklist(check));
        System.out.println(jsonObject);
        return DataResult.successByData(jsonObject);
    }
    /**
     * 分页查询
     *
     * @param checklist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Checklist>> queryByPage(Checklist checklist, PageRequest pageRequest) {
        return ResponseEntity.ok(this.checklistService.queryByPage(checklist, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Checklist> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.checklistService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param checklist 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Checklist> add(Checklist checklist) {
        return ResponseEntity.ok(this.checklistService.insert(checklist));
    }

    /**
     * 编辑数据
     *
     * @param checklist 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Checklist> edit(Checklist checklist) {
        return ResponseEntity.ok(this.checklistService.update(checklist));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.checklistService.deleteById(id));
    }

    /**
     * 查看当天检查单
     * @param patient
     * @return
     */
    @PostMapping("showPaidCheck")
    public DataResult showPaidCheck(@RequestBody Patient patient){
        //获取当天日期
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        System.out.println("当前日期：" + dateNowStr);
        Date appointmentTime= new Date(); //预约时间
        String apTime = "";

        List<preCheck> zww = this.checklistService.showPaidCheck(patient);
        Double totalPrice = 0.0;
        for(preCheck item : zww){
            if(item.getState()!=3){
                totalPrice = totalPrice + item.getCheckitemPrice();
                item.setTotalPrice(totalPrice);
            }
        }
        if( zww.size()!=0 ){
            zww.get(0).setTotalPrice(totalPrice);
            appointmentTime = zww.get(0).getAppointmentTime();
            apTime = sdf.format(appointmentTime);
            System.out.println("预约时间："+apTime);
            //只显示与当前日期一样的处方单
            if(apTime.equals(dateNowStr)){
                System.out.println("我进来了！！！检查单");
                return DataResult.successByDataArray(zww);
            }else {
                System.out.println("检查单空");
                //当天无预约，返回空
                return DataResult.successByData(null);
            }
        }
        //当天无预约，返回空
        return DataResult.successByData(null);
    }

    /**
     * 查看历史检查单
     */
    @PostMapping("showCheck")
    public DataResult showCheck(@RequestBody Checklist checklist){
//        System.out.println("查看检查单");
        System.out.println(checklist.getChecklistId());
        return DataResult.successByData(this.checklistService.showCheck(checklist.getChecklistId()));
    }

    /**
     * 历史检查单项目列表
     * @param checklist
     * @return
     */
    @PostMapping("showHistoryCheck")
    public DataResult showHistoryCheck(@RequestBody Checklist checklist){
        System.out.println("历史进入检查单列表："+checklist.getChecklistId());
        Double totalPrice = 0.0;
        List<preCheck> zww = this.checklistService.showHistoryCheck(checklist.getChecklistId());
        for(preCheck item : zww){
            if(item.getState()!=3){
                totalPrice = totalPrice + item.getCheckitemPrice();
                item.setTotalPrice(totalPrice);
            }
        }
        if( zww.size()!=0){
            zww.get(0).setTotalPrice(totalPrice);
        }
        return DataResult.successByDataArray(zww);
    }

    /**
     * 未缴费检查单
     * @param checklist
     * @return
     */
    @PostMapping("showUnpaidCheck")
    public DataResult showUnpaidCheck(@RequestBody Checklist checklist){
        System.out.println("未缴费检查单列表："+checklist.getChecklistId());
        Double totalPrice = 0.0;
        List<preCheck> zww = this.checklistService.showUnpaidCheck(checklist.getChecklistId());
        for(preCheck item : zww){
            totalPrice = totalPrice + item.getCheckitemPrice();
            item.setTotalPrice(totalPrice);

        }
        if( zww.size()!=0){
            zww.get(0).setTotalPrice(totalPrice);
        }
        return DataResult.successByDataArray(zww);

    }

    /**
     * 支付检查单
     * @param checklist
     * @return
     */
    @PostMapping("payCheck")
    public DataResult payCheck(@RequestBody Checklist checklist){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", this.checklistService.payCheck(checklist.getChecklistId()));
        return DataResult.successByData(jsonObject);

    }


}

