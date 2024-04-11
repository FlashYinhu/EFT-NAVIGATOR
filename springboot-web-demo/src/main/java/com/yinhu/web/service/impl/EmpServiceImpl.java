package com.yinhu.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinhu.web.mappers.EmpMapper;
import com.yinhu.web.pojo.Emp;
import com.yinhu.web.pojo.PageBean;
import com.yinhu.web.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        // 获取总记录数
        Long counts = empMapper.counts();
        Integer start = (page - 1) * pageSize;
        // 获取分页查询的结果
        List<Emp> empList = empMapper.page(start, pageSize);
        // 封装pageBean对象
        PageBean pageBean = new PageBean(counts, empList);
        return pageBean;
    }

    @Override
    public PageBean testPageHelper(Integer page, Integer pagesize) {
        // 设置分页参数
        PageHelper.startPage(page, pagesize);
        // 执行查询
        List<Emp> empList = empMapper.testPageHelper();
        Page<Emp> p = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    /**
     * 在分页查询的基础上进行条件查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean conditionQueue(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<Emp> empList = empMapper.conditionQueue(name, gender, begin, end);
        // 获取查询结果
        Page<Emp> p = (Page<Emp>) empList;
        // 封装pageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void deleteEmp(List<Integer> ids) {
        empMapper.deleteEmp(ids);
    }


    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getEmpByID(id);
        return emp;
    }

    @Override
    public void updateEmp(Emp emp) {
        // 补充基础数据
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    @Override
    public boolean login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }


}
