package com.yinhu.web.controller;

import com.yinhu.web.mappers.EmpMapper;
import com.yinhu.web.pojo.Emp;
import com.yinhu.web.pojo.Result;
import com.yinhu.web.service.EmpService;
import com.yinhu.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录 {}", emp);
        boolean tag = empService.login(emp);

        if (tag){
            // 登录成功 下发密钥
            Map<String, Object> map = new HashMap<>();
            map.put("id", empMapper.getUserIdByUserName(emp.getUsername()));
            map.put("username", emp.getUsername());
            map.put("password", emp.getPassword());
            // jwtStr 令牌中已经包含了登录的员工信息
            String jwtStr = JwtUtils.generateJWT(map);
            log.info("token令牌为: {}", jwtStr);
            return Result.success(jwtStr);
        } else {
            return Result.error("用户或密码名错误");
        }
        // return tag == true ? Result.success("登录成功"):Result.error("用户或密码名错误");
    }

}
