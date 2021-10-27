package com.example.pojo;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-05 12:08:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkMan implements Serializable {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String qq;
    private String email;

}
