package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource dataSource;

    static {
        //读取配置文件里面的数据 替换掉下面写死的内容
        Properties p = new Properties();
        //得到文件输入流
        InputStream ips = DBUtils.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        //把文件加载到属性对象中
        try {
            p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = p.getProperty("db.driver");
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(url,username,password);
        //创建连接池对象
        dataSource = new DruidDataSource();
        //设置数据库连接信息
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //设置连接池初始数量和最大数量
        String initSize = p.getProperty("db.initialSize");
        String maxSize = p.getProperty("db.maxActive");
        dataSource.setInitialSize(Integer.parseInt(initSize));//初始连接数量
        dataSource.setMaxActive(Integer.parseInt(maxSize));//最大连接数量
    }
    //创建获取连接的方法
    public static Connection getConn() throws Exception {

        Connection conn = dataSource.getConnection();//异常抛出
        System.out.println(conn);
        return conn;
    }
}
