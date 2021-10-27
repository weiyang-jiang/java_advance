package com.example.entry;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-07 11:52:49
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageBean<T> implements Serializable {
    private Long totalSize;
    private Long totalPage;
    private Long currentPage;
    private Integer pageSize;
    private List<T> list;
}
