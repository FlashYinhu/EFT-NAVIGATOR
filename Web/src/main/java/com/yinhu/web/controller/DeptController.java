package com.yinhu.web.controller;

import com.yinhu.web.anno.Log;
import com.yinhu.web.pojo.Dept;
import com.yinhu.web.pojo.Emp;
import com.yinhu.web.pojo.Result;
import com.yinhu.web.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



// 可以将公共的请求路径部分抽取到类上
//@RequestMapping("/depts")


@RestController // 自动将返回体变成JSON
public class DeptController {
    // 声明日志记录对象
    // 这句话可以替换为@Slf4j注解，由lombok提供
    private static Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return 规范返回体
     */
    // 下面这句话可以替换为 @GetMapping("/depts")
    @RequestMapping(value = "/depts", method = RequestMethod.GET) // 枚举类型 请求方式为Get
    public Result list(){
        logger.info("请求目的为 查看全部部门数据 开始处理请求");
        // 调用list()业务方法
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门
     * @return
     */
    // 接收路径参数 @PathVariable
    @Log
    @DeleteMapping("/depts/{id}")
    public Result deleteDept(@PathVariable Integer id){ // 获取路径变量的{id}绑定到id
        logger.info("根据id删除部门:{}",id); // {}为参数占位符
        // 事务管理
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept){ // JSON格式的对象如果要封装到对象中需要加上注解@RequestBody
        logger.info("新增部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept){
        logger.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
