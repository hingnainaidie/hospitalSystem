package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.Office;
import com.cqu.hospitalsystem.dao.OfficeDao;
import com.cqu.hospitalsystem.service.OfficeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Office)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:00:57
 */
@Service("officeService")
public class OfficeServiceImpl implements OfficeService {
    @Resource
    private OfficeDao officeDao;
    @Override
    public List<Office> allOffice(){
        return this.officeDao.allOffice();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param officeId 主键
     * @return 实例对象
     */
    @Override
    public Office queryById(Long officeId) {
        return this.officeDao.queryById(officeId);
    }

    /**
     * 分页查询
     *
     * @param office 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Office> queryByPage(Office office, PageRequest pageRequest) {
        long total = this.officeDao.count(office);
        return new PageImpl<>(this.officeDao.queryAllByLimit(office, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param office 实例对象
     * @return 实例对象
     */
    @Override
    public Office insert(Office office) {
        this.officeDao.insert(office);
        return office;
    }

    /**
     * 修改数据
     *
     * @param office 实例对象
     * @return 实例对象
     */
    @Override
    public Office update(Office office) {
        this.officeDao.update(office);
        return this.queryById(office.getOfficeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param officeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long officeId) {
        return this.officeDao.deleteById(officeId) > 0;
    }
}
