package day02.连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 16:32:23
 */

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class demo {
    public static void main(String[] args) throws SQLException {
        MyDataSource3 myDataSource3 = new MyDataSource3();
        Connection connection1 = myDataSource3.getConnection();
        Connection connection2 = myDataSource3.getConnection();
        Connection connection3 = myDataSource3.getConnection();
        Connection connection4 = myDataSource3.getConnection();
        Connection connection5 = myDataSource3.getConnection();
        Connection connection6 = myDataSource3.getConnection(); // 连接池里面连接总共有5个， 第六个是创建出来的新的

        connection1.close();
        connection2.close();
        connection3.close();
        connection4.close();
        connection5.close();
        connection6.close();
        // 前面五个调用close方法都是在装饰类中进行归还连接到连接池， 第六个连接不属于装饰对象，所以直接调用源代码的close方法
    }
    @Test
    public void test01() throws SQLException {
        MyDataSource4 myDataSource4 = new MyDataSource4();
        Connection connection1 = myDataSource4.getConnection();
        Connection connection2 = myDataSource4.getConnection();
        Connection connection3 = myDataSource4.getConnection();
        Connection connection4 = myDataSource4.getConnection();
        Connection connection5 = myDataSource4.getConnection();
        Connection connection6 = myDataSource4.getConnection(); // 连接池里面连接总共有5个， 第六个是创建出来的新的

        connection1.close();
        connection2.close();
        connection3.close();
        connection4.close();
        connection5.close();
        connection6.close();
        // 前面五个调用close方法都是在装饰类中进行归还连接到连接池， 第六个连接不属于装饰对象，所以直接调用源代码的close方法
    }
}
