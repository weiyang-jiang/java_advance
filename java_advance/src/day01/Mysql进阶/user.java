package day01.Mysql进阶;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-31 17:53:18
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class user implements Serializable {
    // int默认值为0， 不能序列化, Integer 默认为null， 可以序列化
    // 最好实现序列化接口
    private Integer id;
    private String ename;
    private String loc;
}
