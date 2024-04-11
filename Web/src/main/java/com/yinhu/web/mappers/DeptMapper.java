package com.yinhu.web.mappers;

import com.yinhu.web.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门数据的Mapper方法 简单SQL
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();


    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("INSERT INTO dept (id, name, create_time, update_time) values (#{id}, #{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 更新部门信息
     * @param dept
     */
    @Update("UPDATE dept set (id, name, create_time, update_time) = (#{id}, #{name}, #{createTime}, #{updateTime})")
    void update(Dept dept);

}
