package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.preCheck;
import com.cqu.hospitalsystem.entity.Checkitem;
import com.cqu.hospitalsystem.dao.CheckitemDao;
import com.cqu.hospitalsystem.service.CheckitemService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Checkitem)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 16:58:22
 */
@Service("checkitemService")
public class CheckitemServiceImpl implements CheckitemService {
    @Resource
    private CheckitemDao checkitemDao;

    /**
     * 通过ID查询单条数据
     *
     * @param checkitemId 主键
     * @return 实例对象
     */
    @Override
    public Checkitem queryById(Long checkitemId) {
        return this.checkitemDao.queryById(checkitemId);
    }
    @Override
    public List<Checkitem> allCheckitem() {
        return this.checkitemDao.allCheckitem();
    }

    /**
     * 分页查询
     *
     * @param checkitem 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Checkitem> queryByPage(Checkitem checkitem, PageRequest pageRequest) {
        long total = this.checkitemDao.count(checkitem);
        return new PageImpl<>(this.checkitemDao.queryAllByLimit(checkitem, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param checkitem 实例对象
     * @return 实例对象
     */
    @Override
    public Checkitem insert(Checkitem checkitem) {
        this.checkitemDao.insert(checkitem);
        return checkitem;
    }

    /**
     * 修改数据
     *
     * @param checkitem 实例对象
     * @return 实例对象
     */
    @Override
    public Checkitem update(Checkitem checkitem) {
        this.checkitemDao.update(checkitem);
        return this.queryById(checkitem.getCheckitemId());
    }

    /**
     * 通过主键删除数据
     *
     * @param checkitemId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long checkitemId) {
        return this.checkitemDao.deleteById(checkitemId) > 0;
    }

    @Override
    public boolean cancelCheck(preCheck preCheck) {
        return this.checkitemDao.cancelCheck(preCheck);
    }
}
