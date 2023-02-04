package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.Recordtemplate;
import com.cqu.hospitalsystem.dao.RecordtemplateDao;
import com.cqu.hospitalsystem.service.RecordtemplateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Recordtemplate)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:02:04
 */
@Service("recordtemplateService")
public class RecordtemplateServiceImpl implements RecordtemplateService {
    @Resource
    private RecordtemplateDao recordtemplateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rtemplateId 主键
     * @return 实例对象
     */
    @Override
    public Recordtemplate queryById(Long rtemplateId) {
        return this.recordtemplateDao.queryById(rtemplateId);
    }
    @Override
    public Recordtemplate searchTemplate(Recordtemplate recordtemplate){
        return this.recordtemplateDao.searchTemplate(recordtemplate);
    }

    @Override
    public boolean saveTemplate(Recordtemplate recordtemplate){
        return this.recordtemplateDao.saveTemplate(recordtemplate);
    }
    /**
     * 分页查询
     *
     * @param recordtemplate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Recordtemplate> queryByPage(Recordtemplate recordtemplate, PageRequest pageRequest) {
        long total = this.recordtemplateDao.count(recordtemplate);
        return new PageImpl<>(this.recordtemplateDao.queryAllByLimit(recordtemplate, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param recordtemplate 实例对象
     * @return 实例对象
     */
    @Override
    public Recordtemplate insert(Recordtemplate recordtemplate) {
        this.recordtemplateDao.insert(recordtemplate);
        return recordtemplate;
    }

    /**
     * 修改数据
     *
     * @param recordtemplate 实例对象
     * @return 实例对象
     */
    @Override
    public Recordtemplate update(Recordtemplate recordtemplate) {
        this.recordtemplateDao.update(recordtemplate);
        return this.queryById(recordtemplate.getRtemplateId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rtemplateId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long rtemplateId) {
        return this.recordtemplateDao.deleteById(rtemplateId) > 0;
    }
}
