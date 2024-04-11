package com.yinhu.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询分装结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total; // 总记录数
    private List<Emp> rows; // 数据列表
}
