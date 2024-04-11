package com.yinhu.web.service;

import com.yinhu.web.pojo.Emp;
import com.yinhu.web.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 进行分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize);

    /**
     * 利用pageHelper进行分页查询
     * @param page
     * @param pagesize
     * @return
     */
    PageBean testPageHelper(Integer page, Integer pagesize);

    /**
     * 条件查询业务
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean conditionQueue(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据传入路径参数 批量删除员工
     * @param ids
     */
    void deleteEmp(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void addEmp(Emp emp);

    Emp getById(Integer id);

    /**
     * 业务接口更新员工信息
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 登录接口
     * @param emp
     * @return
     */
    boolean login(Emp emp);
}
