package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginActionServlet",urlPatterns = "/loginaction")
public class LoginActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");
        //获取传递过来的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rem = request.getParameter("rem");
        System.out.println(username+":"+password+":"+rem);
        //创建Dao并调用login方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if(user!=null){//登陆成功
            //判断是否需要记住用户名和密码
            if(rem!=null ){//需要记住用户名和密码
                //如果保存到cookie中的内容有中文,老版本存在乱码问题
                //username = URLEncoder.encode(username,"UTF-8");
                //从Cookie中取出中文内容时需要对取出的内容进行URL解码操作
                //username = URLDecoder.decode(username,"UTF-8");
                //创建Cookie对象把用户名和密码保存
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //设置cookie保存时间
                c1.setMaxAge(60*60*24);
                //把cookie下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            //记住登录状态
            HttpSession session = request.getSession();
            //把登录成功的用户对象  保存到Session中
            session.setAttribute("user",user);


            response.sendRedirect("/home");
        }else{//登录失败
            //如果登录失败则重定向到 登录页面
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
