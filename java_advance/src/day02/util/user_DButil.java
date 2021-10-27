package day02.util;

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
public class user_DButil implements Serializable {
    // int默认值为0， 不能序列化, Integer 默认为null， 可以序列化
    // 最好实现序列化接口
    private Integer id;
    private String username;
    private String password;
}
