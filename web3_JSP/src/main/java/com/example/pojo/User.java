package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-25 13:44:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String money;
//    private Integer age;
//    private String gender;

}
