package day02.util;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-31 18:19:28
 */




import java.sql.*;
import java.util.ResourceBundle;


public class JdbcUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String DriverPath;

    static {
        try {
            ResourceBundle jdbc = ResourceBundle.getBundle("jdbc"); // 这个方法仅限于在properties文件中使用
            username = jdbc.getString("jdbc.username");
            password = jdbc.getString("jdbc.password");
            url = jdbc.getString("jdbc.url");
            DriverPath = jdbc.getString("jdbc.driverClassName");
//            // 读取配置文件, 使用类加载器
//            InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//            Properties properties = new Properties();
//            // 加载配置文件
//            properties.load(resourceAsStream);
//
//            url = properties.getProperty("jdbc.url");
//            username = properties.getProperty("jdbc.username");
//            password = properties.getProperty("jdbc.password");
//            DriverPath = properties.getProperty("jdbc.driverClassName");

            Class.forName(DriverPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {

        // 创建连接

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    // 当查询的时候用这个方法
    public static void close(ResultSet rst, Statement stm, Connection connection){
        try {
            rst.close();
            stm.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void ResourceBundle_read(){

    }
    // 增删改用这个方法
    public static void close(Statement stm, Connection connection){
        close(null, stm, connection);
    }

}
