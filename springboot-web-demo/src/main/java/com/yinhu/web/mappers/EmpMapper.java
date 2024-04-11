package com.yinhu.web.mappers;

import com.yinhu.web.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数字
     * @return 返回一个Long类型的整数
     */
    @Select("select (count(*)) from emp")
    Long counts();

    /**
     * 进行分页查询 获取列表数据的方法
     * @param start 起始页码
     * @param pageSize 页面大小
     * @return
     */
    @Select("select * from emp limit #{pageSize} offset #{start}")
    List<Emp> page(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    /**
     * 使用分页插件查询员工信息
     * @return
     */
    @Select("select  * from emp")
    List<Emp> testPageHelper();

    /**
     * 复杂查询 不使用注解 而使用xml配置文件的方式进行查询
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    List<Emp> conditionQueue(@Param("name") String name,
                             @Param("gender") Short gender,
                             @Param("begin") LocalDate begin,
                             @Param("end") LocalDate end);


    /**
     * 批量删除员工
     * @param ids
     */
    void deleteEmp(@Param("ids") List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void addEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getEmpByID(Integer id);

    /**
     * 动态sql更新emp
     * @param emp
     */
    void updateEmp(Emp emp);


    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    boolean getByUsernameAndPassword(Emp emp);

    @Select("SELECT id FROM emp WHERE username = #{name}")
    Integer getUserIdByUserName(String name);

    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptID(Integer id);
}
