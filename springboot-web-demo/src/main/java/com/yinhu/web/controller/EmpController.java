package com.yinhu.web.controller;

import com.yinhu.web.anno.Log;
import com.yinhu.web.pojo.Emp;
import com.yinhu.web.pojo.PageBean;
import com.yinhu.web.pojo.Result;
import com.yinhu.web.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController

public class EmpController {

    @Autowired
    private EmpService empService;
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        // 设置默认参数
        // 方法一 对传入参数进行空值判断
        // 方法二 传入参数注解@RequestParam
//        if (page == null) page = 1;
//        if (pageSize == null) pageSize = 10;
        log.info("进行分页查询:{},{}", page, pageSize);
        PageBean pageBean = empService.page(page, pageSize);
        return Result.success(pageBean);
    }


    /**
     * 测试分页查询插件
     * @return
     */
    @GetMapping("/emps/testPageHelper")
    public Result testPageHelper(){
        log.info("测试分页插件进行查询");
        Integer page = 1;
        Integer pagesize = 6;
        PageBean pageBean = empService.testPageHelper(page, pagesize);
        return Result.success(pageBean);
    }

    @GetMapping("/emps/conditionQueue")
    public Result conditionQueue(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 String name,
                                 @RequestParam Short gender,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("条件查询 {}{}{}{}{}{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.conditionQueue(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    //在idea中，默认情况下是不会去编译src/main/java下的xml文件的！！！
    //在idea中，默认情况下是不会去编译src/main/java下的xml文件的！！！
    @Log
    @DeleteMapping("/emps/delteEmp/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }

    /**
     * 新增员工
     * @param emp 请求参数为json格式
     * @return
     */
    @Log
    @PostMapping("/emps")
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工 emp : {}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result getByID(@PathVariable Integer id){
        log.info("根据id查询员工信息{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    // 更新操作 请求方式为put
    @Log
    @PutMapping("/emps")
    public Result updateEmp(@RequestBody Emp emp){
        log.info("更新员工的信息: {}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }

}
