package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(String username, String password){
        //获取连接
        try (Connection conn = DBUtils.getConn()){
            String sql = "select id from vrduser where username = ? and password = ?";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            //如果有下一条数据说明查到了  算是登录成功
            if(rs.next()){
                int id = rs.getInt(1);
                //把查询到的用户信息封装到对象中返回出去
                return new User(id,username,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
