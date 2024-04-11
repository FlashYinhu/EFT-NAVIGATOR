package com.yinhu.web.service;

import com.yinhu.web.pojo.Dept;
import com.yinhu.web.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);


    /**
     * 新增部门
     */
    void add(Dept dept);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);


}
