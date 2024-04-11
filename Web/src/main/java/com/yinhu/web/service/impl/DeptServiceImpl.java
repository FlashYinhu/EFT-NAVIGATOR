package com.yinhu.web.service.impl;

import com.yinhu.web.aop.MyTag;
import com.yinhu.web.mappers.DeptMapper;
import com.yinhu.web.mappers.EmpMapper;
import com.yinhu.web.pojo.Dept;
import com.yinhu.web.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @MyTag // 添加自定义注解 测试@annotation 根据注解定义切入点
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * 删除部门的时候 会删除该部门下的员工
     * @param id
     */
    // @Transactional
    // 用在业务层的方法 类 接口上 将当前方法交给spring进行事务管理 方法执行前开启事务 成功执行完毕 提交事务
    // 出现异常 回滚事务
    // 推荐加在业务层的增删改操作上（多次数据访问） 查询不用 因为查询不会更改数据库中的数据
    @Transactional(rollbackFor = Exception.class) // 当前方法已经交给Spring进行事务管理 出现所有的异常都会回滚
    @Override
    public void delete(Integer id) {
        // 删除部门
        deptMapper.deleteById(id);
        // 部门解散 员工删除
        empMapper.deleteByDeptID(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setId(new Random().nextInt(100));
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);

    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
