package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> records; // 当前页数据
    private long total;      // 总记录数
    private int current;     // 当前页码
    private int size;        // 每页大小
}

