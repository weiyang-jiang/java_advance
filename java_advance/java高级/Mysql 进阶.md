# Mysql 进阶

## 一、拆表

1. 外键约束：constraint 外键名字 foreign key(从表关连的字段名) references department(主表关连的字段名)
2. 删除外键：alter table employee drop foreign key fk1;

3. 外键的级联操作：constraint 外键名字 foreign key(从表关连的字段名) references department(主表关连的字段名) on update cascade on delete cascade

   // 主表更新，从表也会更新， 主表删除，从表相应字段记录也会删除

## 二、建表原则

```mysql
一对一建表：一般比较少见， 

多对多建表：比如键盘属于外设，也属于电子设备。外设也包括鼠标和显示器，电子设备也可以有很多商品。需要创建第三张表来关联

一对多建表：部门和员工的关系
```

## 三、多表查询

```mysql
1. 交叉连接查询， select * from a,b; 左表和右表进行乘积组合。 没有太大意义

-- 隐式内连接
2. select * from department d, employee e where d.dept_id = e.emp_dept_id ORDER BY age DESC;
-- 显式内连接
3. SELECT * from department d INNER JOIN employee e on d.dept_id = e.emp_dept_id;
-- 左外连接
4. SELECT * from department d LEFT JOIN employee e on d.dept_id = e.emp_dept_id; // 左外连接，以左表为主，必须显示全左表信息。
-- 右外连接
5. SELECT * FROM department d RIGHT JOIN employee e on d.dept_id = e.emp_dept_id; // 以右表为主， 显示全右表信息。


```

## 四、子查询

```mysql
查询嵌套：案例1--查询年龄最大的人的姓名， 一般都结合聚合函数
SELECT e.emp_name from employee e where e.age = (SELECT max(age) FROM employee);

--查询年龄大于19的人的部门
SELECT * from department d where d.dept_id in (SELECT e.emp_dept_id from employee e where e.age > 19); 

--查询开发部和销售部的所有员工信息
SELECT * FROM employee WHERE emp_dept_id in (SELECT dept_id FROM department where dept_name in ("销售部", "研发部"));

--查询出年龄大于19的员工信息和部门名称
SELECT n.emp_id, n.emp_name, n.age, d.dept_name FROM (SELECT * FROM employee WHERE age > 19) as n, department d where n.emp_dept_id = d.dept_id;
```

## 五、事务

```mysql
事务是指逻辑上的一组操作， 要不全部成功，要不全部失败，不允许发生成功一半的情况。例如转账，一件事情的发生必须导致另外一件事情的结果
-- 开启事务
START TRANSACTION;
UPDATE account set money = money - 100 WHERE name = 'zs';
UPDATE account set money = money + 100 WHERE name = 'ls';
-- 失败了回滚
ROLLBACK;
-- 成功了提交
COMMIT;
SELECT * from account;
```

```mysql
-- 设置回滚点，在插入大量数据的时候，插入回滚点就可以像打游戏存档一样。
START TRANSACTION;
INSERT INTO account VALUES(null, "zs1", 1000);
INSERT INTO account VALUES(null, "ls1", 1000);
INSERT INTO account VALUES(null, "ww1", 1000);
INSERT INTO account VALUES(null, "ww2", 1000);
INSERT INTO account VALUES(null, "ww3", 1000);
-- 设置回滚点
SAVEPOINT a;
INSERT INTO account VALUES(null, "ww4", 1000);
INSERT INTO account VALUES(null, "ww5", 1000);
SAVEPOINT b;
ROLLBACK to a;
-- 事务是具有原子性和一致性的，持久性，一旦被提交就是永久性。
```

### 并发操作数据库时

```mysql
-- 查询事务隔离等级
SELECT @@tx_isolation;
-- 不提交，可能会发生脏读，不可重复读，还有幻读的情况
SET SESSION TRANSACTION ISOLATION LEVEL read UNCOMMITTED;
-- 提交，不会发生脏读，会发生不可重复读，还有幻读的情况
SET SESSION TRANSACTION ISOLATION LEVEL read COMMITTED;  // orcel 等级
-- 重复读，不会发生脏读，不可重复读，会发生幻读的情况
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ; // mysql 常用等级
-- 序列读，不会发生脏读，不可重复读，还有幻读的情况
SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;

等级越高，效率越低
```

### 数据库的备份和还原

```mysql
mysqldump -u用户名 -p密码 数据库 > 文件路径

-- 还原
source 文件路径
```

### 数据库设计的三大范式

```mysql
1. 每一列都不可再拆分， 原子性。
2. 在满足第一范式的情况下，表中每个列必须依赖主键。
3. 第三范式拆表，如果出现其他列不依赖于主键，需要拆表，设立外键。
```

### 函数使用

```mysql
-- if(ex1, ex2, ex3) 如果ex1为true，那么执行ex2, 如果为false，那么执行ex3.
-- concat_ws(分隔符， "hello", uname)
-- concat(str1, str2) 合并字符串
-- trim(uname) 去空格
-- 
```



### 练习

```mysql
-- 部门表
CREATE TABLE dept (
  id INT PRIMARY KEY PRIMARY KEY, -- 部门id
  dname VARCHAR(50), -- 部门名称
  loc VARCHAR(50) -- 部门位置
);

-- 添加4个部门
INSERT INTO dept(id,dname,loc) VALUES 
(10,'教研部','北京'),
(20,'学工部','上海'),
(30,'销售部','广州'),
(40,'财务部','深圳');

-- 职务表，职务名称，职务描述
CREATE TABLE job (
  id INT PRIMARY KEY,
  jname VARCHAR(20),
  description VARCHAR(50)
);

-- 添加4个职务
INSERT INTO job (id, jname, description) VALUES
(1, '董事长', '管理整个公司，接单'),
(2, '经理', '管理部门员工'),
(3, '销售员', '向客人推销产品'),
(4, '文员', '使用办公软件');

-- 员工表
CREATE TABLE emp (
  id INT PRIMARY KEY, -- 员工id
  ename VARCHAR(50), -- 员工姓名
  job_id INT, -- 职务id
  mgr INT , -- 上级领导
  joindate DATE, -- 入职日期
  salary DECIMAL(7,2), -- 工资
  bonus DECIMAL(7,2), -- 奖金
  dept_id INT, -- 所在部门编号
  CONSTRAINT emp_jobid_ref_job_id_fk FOREIGN KEY (job_id) REFERENCES job (id),
  CONSTRAINT emp_deptid_ref_dept_id_fk FOREIGN KEY (dept_id) REFERENCES dept (id)
);

-- 添加员工
INSERT INTO emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id) VALUES 
(1001,'孙悟空',4,1004,'2000-12-17','8000.00',NULL,20),
(1002,'卢俊义',3,1006,'2001-02-20','16000.00','3000.00',30),
(1003,'林冲',3,1006,'2001-02-22','12500.00','5000.00',30),
(1004,'唐僧',2,1009,'2001-04-02','29750.00',NULL,20),
(1005,'李逵',4,1006,'2001-09-28','12500.00','14000.00',30),
(1006,'宋江',2,1009,'2001-05-01','28500.00',NULL,30),
(1007,'刘备',2,1009,'2001-09-01','24500.00',NULL,10),
(1008,'猪八戒',4,1004,'2007-04-19','30000.00',NULL,20),
(1009,'罗贯中',1,NULL,'2001-11-17','50000.00',NULL,10),
(1010,'吴用',3,1006,'2001-09-08','15000.00','0.00',30),
(1011,'沙僧',4,1004,'2007-05-23','11000.00',NULL,20),
(1012,'李逵',4,1006,'2001-12-03','9500.00',NULL,30),
(1013,'小白龙',4,1004,'2001-12-03','30000.00',NULL,20),
(1014,'关羽',4,1007,'2002-01-23','13000.00',NULL,10);

-- 工资等级表
CREATE TABLE salarygrade (
  grade INT PRIMARY KEY,
  losalary INT,
  hisalary INT
);

-- 添加5个工资等级
INSERT INTO salarygrade(grade,losalary,hisalary) VALUES 
(1,7000,12000),
(2,12010,14000),
(3,14010,20000),
(4,20010,30000),
(5,30010,99990);

-- 1.查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述
SELECT e.id, e.ename, e.salary, j.jname, j.description FROM emp e, job j WHERE j.id = e.job_id;

-- 2.查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述，部门名称，部门位置
SELECT n.id, n.ename, n.salary, n.jname, n.description, d.dname, d.loc FROM (SELECT e.id, e.ename, e.salary, j.jname, j.description, e.dept_id FROM emp e, job j WHERE j.id = e.job_id) n, dept d WHERE n.dept_id = d.id;

-- 3.查询所有员工信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
SELECT n1.id, n1.ename, n1.salary, n1.jname, n1.description, n1.dname, n1.loc, s.grade FROM salarygrade s, (SELECT n.id, n.ename, n.salary, n.jname, n.description, d.dname, d.loc FROM (SELECT e.id, e.ename, e.salary, j.jname, j.description, e.dept_id FROM emp e, job j WHERE j.id = e.job_id) n, dept d WHERE n.dept_id = d.id) n1 WHERE s.losalary <= n1.salary and s.hisalary > n1.salary;

-- 4.查询经理的信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
SELECT * FROM (SELECT n1.id, n1.ename, n1.salary, n1.jname, n1.description, n1.dname, n1.loc, s.grade FROM salarygrade s, (SELECT n.id, n.ename, n.salary, n.jname, n.description, d.dname, d.loc FROM (SELECT e.id, e.ename, e.salary, j.jname, j.description, e.dept_id FROM emp e, job j WHERE j.id = e.job_id) n, dept d WHERE n.dept_id = d.id) n1 WHERE s.losalary <= n1.salary and s.hisalary > n1.salary) n2 WHERE n2.jname = '经理';

-- 5.查询出部门编号、部门名称、部门位置、部门人数
SELECT d.id, d.dname, d.loc, n.total 部门人数 FROM (SELECT count(*) total, dept_id FROM emp e GROUP BY dept_id) n INNER JOIN dept d ON n.dept_id = d.id;

-- 6.查询所有员工信息。显示员工信息和部门名称，没有员工的部门也要显示
SELECT * FROM emp e RIGHT JOIN dept d ON e.dept_id = d.id;

-- 7.查询所有员工信息。显示员工姓名，员工工资，职务名称，工资等级，并按工资升序排序
SELECT n.ename, n.salary, n.jname, s.grade FROM salarygrade s, (SELECT e.ename, e.salary, j.jname FROM emp e, job j WHERE e.job_id = j.id) n WHERE s.losalary <= n.salary and s.hisalary > n.salary ORDER BY n.salary DESC;

-- 8.列出所有员工的姓名及其直接上级的姓名,没有领导的员工也需要显示  
SELECT e.ename, IFNULL(m.ename,'没有') '上司' FROM emp e LEFT JOIN emp m ON e.mgr = m.id;

-- 9.查询入职期早于直接上级的所有员工编号、姓名、部门名称
SELECT n.id ,n.ename, d.dname FROM (SELECT e.id, e.ename, e.dept_id FROM emp e LEFT JOIN emp m ON e.mgr = m.id WHERE e.joindate > m.joindate) n, dept d WHERE n.dept_id = d.id;

-- 10.查询工资高于公司平均工资的所有员工信息。显示员工信息，部门名称，上级领导，工资等级
SELECT n1.ename, n1.salary, n1.bonus, n1.joindate, n1.dname, n1.mname '上司', s.grade FROM salarygrade s, (SELECT * FROM (SELECT e.ename, e.joindate, e.salary, e.bonus, IFNULL(m.ename,'没有') as mname, e.dept_id FROM (SELECT * FROM emp WHERE salary > (SELECT avg(salary) FROM emp)) e LEFT JOIN emp m ON e.mgr = m.id) n, dept d WHERE n.dept_id = d.id) n1 WHERE s.losalary <= n1.salary and s.hisalary > n1.salary ORDER BY n1.salary DESC;

SELECT e.id, e.ename, e.salary, e.bonus, e.joindate, d.dname, m.ename, s.grade FROM emp e INNER JOIN emp m INNER JOIN dept d INNER JOIN salarygrade s WHERE e.dept_id = d.id  AND e.mgr = m.id AND e.salary BETWEEN s.losalary AND s.hisalary AND e.salary > (SELECT avg(salary) FROM emp);

SELECT e.*, d.`dname`, m.`ename`, s.`grade` FROM emp e INNER JOIN emp m INNER JOIN dept d INNER JOIN salarygrade s WHERE e.dept_id=d.id AND e.mgr=m.id AND e.salary BETWEEN s.losalary AND hisalary AND e.salary>(SELECT AVG(salary) FROM emp);

```

