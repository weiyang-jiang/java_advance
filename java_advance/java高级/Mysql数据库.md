# Mysql数据库 

## 一、数据库操作 (DDL操作)

1. create database 数据库名字 [ character set 数据集] [collate 校对规则];  // 创建数据库
2. show databases; //查询数据库
3. show create database 数据库名字; // 查询数据库的结构
4. drop database 数据库名字; // 删除数据库
5. alter database 数据库名字 character set utf8; // 修改数据库的字符集
6. use 数据库名字; // 使用数据库
7. select 数据库名字; // 查看一下正在使用哪一个数据库

## 二、表操作
1. create table 表名(

    ```mysql
    tinyint // -128-127
    bool, boolen //0或者1
    smallint // 2^16
    int // 2^32
    bigint // 2^64
    float 小数
    double 精度更大的小数
    
    char(size)  //固定size长度的字符串，正好size个
    varchar(size) // 可变长度的字符串, 最大存size个
    blob // 二进制文本
    text //  大文本
    
    date // 年月日
    datatime // 年月日时分秒
    TimeStamp // 时间戳
    ```
    )


2. primary key主键（唯一 unique，非空 not null， 自增 auto_increment)

    ```mysql
    create table student(
    id int primary key auto_increment,
    name varchar(40),
    sex varchar(13) not null
    );
    ```
    
3. desc student; 查看表结构

4. alter table 表名 add 字段名 字段类型 约束; //增加一个字段

5. alter table 表名 modify 字段名 字段类型 约束; //修改一个字段的类型和约束， 不能修改名字

6. alter table 表名 change 旧字段名 新字段名 字段类型 约束; //修改一个字段的名称，类型和约束

7. alter table 表名 drop 字段名; // 删除字段

8. rename table 表名 to 新的表名;  //修改表的名字

9. drop table 表名; // 删除表

## 三、操作表中数据
1. insert into 表名(字段名，字段名）value(值，值); //添加元素， 一一对应

2. insert into 表名 values (对应的所有值，按照顺序排列);

3. uqdate 表名 set 字段名 = 值， 字段名=值 [where 条件]; //修改数据

4. delete from 表名 [where 条件]; //删除记录， 自增的数字还会累加

5. truncate table product; // 自增的数字会直接清零，物理删除，特别危险

## 四、查询

1. select [*] [字段名， 字段名] from 表名 [where 条件]; //查询所有记录

2. select distinct 字段 from 表名; //重复的字段数据不会显示

3. select * from 表; // 查询所有数据

4.  select 字段名 as 别名 from 表 where 别名的条件;

5.  select [price * num] from 表名; 

6. between .. and ... 显示某一区间的值

7. in(1, 2, 3, 4, 9, ...) 满足列表中的值

8. like '张_'  下划线表示名字是两个字

9. like '张%' 开头为张，字数没有 限制、

10.  is null 判断是否为空

11. and 多个条件同时成立

12. or 多个条件成立一个

13. order by 字段名 desc; // 降序排序

14. order by 字段名 asc; // 升序排序

## 五、聚合函数

1. max(字段名); // 最大值

2. min(字段名); // 最小值

3. avg(字段名); // 

4. select 聚合函数max(score) from student where 条件; // 

5. sum(); // 总和

6. count(); 统计个数

7. ifnull(sorce, 0); // 如果为空，那么就为0.

8. select 字段名, 聚合函数 from 表名 group by 同一字段名; // 
        select sex, count(*) from student group by sex;

9. select sex, count(*) from student group by sex having count(*) > 5; 
        这里用having来替代where

10. select sex, count(*) from student where id < 8 group by sex having count(*) > 2;

11. select ... from ... limit a, b; 分页查询，从哪一条数据开始， b是表示查几条？

## 六、日期函数

1. now(); // select now(); 获取时分秒
2.  curtime(); // select time(); 获取当前年月日
3.  date_format(date, format); select date_format <'2010-10-11', '%Y年%月%日'>
4.  select str_to_date<'2010-10-11', ''>

## 七、字符串函数

1. length(str)
2. concat(str1, str2)
3. substring(str, pos), 
4. trim(both|leading|trailing) 去除前后缀， 去除前缀， 去除后缀
5. replace(str, from_str, to_str)
6. insert(str, pos, len, newstr); 在指定位置替换多少个字符串。
7. abs(x) 绝对值
8. ceil(x) 向上取整
9. floor(x) 向下取整
10. pow(x) 取2的三次幂
11. rand() 取0到100的随机数

