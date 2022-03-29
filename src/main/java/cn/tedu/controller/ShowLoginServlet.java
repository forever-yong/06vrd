package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "ShowLoginServlet" , urlPatterns = "/login")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = new Context();
        //取出Cookie中的用户名和密码显示到页面中
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            //遍历Cookies
            for(Cookie c:cookies){
                //判断是否是用户名
                if(c.getName().equals("username")){
                    //从Cookie中取出中文内容时需要对取出的内容进行URL解码操作
                    String username = URLDecoder.decode(c.getValue(),"UTF-8");
                    //把用户名存到容器中
                    context.setVariable("username",username);
                }
                //判断是否是密码
                if(c.getName().equals("password")){
                    //把密码保存到容器中
                    context.setVariable("password",c.getValue());
                }
            }
        }
        ThUtils.print("login.html",context,response);
    }
}
