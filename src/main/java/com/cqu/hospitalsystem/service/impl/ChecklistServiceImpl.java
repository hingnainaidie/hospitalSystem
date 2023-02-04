package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.Check;
import com.cqu.hospitalsystem.Vo.checklistVo;
import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.dao.IdRelationDao;
import com.cqu.hospitalsystem.dao.ItemListRelationDao;
import com.cqu.hospitalsystem.entity.Checklist;
import com.cqu.hospitalsystem.dao.ChecklistDao;
import com.cqu.hospitalsystem.entity.Patient;
import com.cqu.hospitalsystem.service.ChecklistService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Checklist)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 16:58:52
 */
@Service("checklistService")
public class ChecklistServiceImpl implements ChecklistService {
    @Resource
    private ChecklistDao checklistDao;
    @Resource
    private IdRelationDao idRelationDao;
    @Resource
    private ItemListRelationDao itemListRelationDao;

    @Override
    public boolean saveChecklist(Check check) throws ParseException {
        String temp=check.getPrescribeTime();
        Long regId=check.getRegId();
        List<Long> checkitemList= check.getCheckitemList();

        //checklist对象
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date prescribeTime = fmt.parse(temp);

        System.out.println(temp);
        System.out.println(prescribeTime);

        Checklist checklist=new Checklist();
        checklist.setChecklistStatus(0);
        checklist.setPrescribeTime(prescribeTime);

        int isInserted=this.checklistDao.insert(checklist);
        if(isInserted!=0){ //插入成功
            Long cId=this.checklistDao.getChecklistId(); //拿到刚插入的id
            boolean flaginsert=false;
            //1、更新或插入关联表
            if(this.idRelationDao.findbyregId(regId)==null){ //没有记录
                flaginsert=this.idRelationDao.insert_idRecheck(regId,cId);
            }
            else{ //有记录
                flaginsert=this.idRelationDao.update_idRecheck(regId,cId);
            }

            //2、插入pres_medi关联表
            if(flaginsert){
                int len=checkitemList.size();
                for(int i=0;i<len;i++){
                    boolean flag=this.itemListRelationDao.insertItemList(checkitemList.get(i),cId,0);
                    if(flag) continue;
                    else{
                        System.out.println("item_list关联表更新失败！！");
                    }
                }
                return true;
            }
            else{
                System.out.println("id关联表没有更新！！");
                return false;
            }
        }
        else{
            System.out.println("检查表没有插入！！");
            return false;
        }
    }

    /**
     * 通过ID查询单条数据
     *
     * @param checklistId 主键
     * @return 实例对象
     */
    @Override
    public Checklist queryById(Long checklistId) {
        return this.checklistDao.queryById(checklistId);
    }

    /**
     * 分页查询
     *
     * @param checklist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Checklist> queryByPage(Checklist checklist, PageRequest pageRequest) {
        long total = this.checklistDao.count(checklist);
        return new PageImpl<>(this.checklistDao.queryAllByLimit(checklist, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param checklist 实例对象
     * @return 实例对象
     */
    @Override
    public Checklist insert(Checklist checklist) {
        this.checklistDao.insert(checklist);
        return checklist;
    }

    /**
     * 修改数据
     *
     * @param checklist 实例对象
     * @return 实例对象
     */
    @Override
    public Checklist update(Checklist checklist) {
        this.checklistDao.update(checklist);
        return this.queryById(checklist.getChecklistId());
    }

    /**
     * 通过主键删除数据
     *
     * @param checklistId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long checklistId) {
        return this.checklistDao.deleteById(checklistId) > 0;
    }

    @Override
    public List<preCheck> showPaidCheck(Patient patient) {
        return this.checklistDao.showPaidCheck(patient);
    }

    /**
     * 查看历史检查单
     * @param checklistId
     * @return
     */
    @Override
    public checklistVo showCheck(Long checklistId) {
        return this.checklistDao.showCheck(checklistId);
    }

    /**
     * 查看历史检查单检查项目列表
     * @param checklistId
     * @return
     */
    @Override
    public List<preCheck> showHistoryCheck(Long checklistId) {
        return this.checklistDao.showHistoryCheck(checklistId);
    }

    /**
     * 未缴费检查单列表
     * @param checklistId
     * @return
     */
    @Override
    public List<preCheck> showUnpaidCheck(Long checklistId) {
        return this.checklistDao.showUnpaidCheck(checklistId);
    }

    /**
     * 支付检查单
     * @param checklistId
     * @return
     */
    @Override
    public boolean payCheck(Long checklistId) {
        return this.checklistDao.payCheck(checklistId);
    }


}
