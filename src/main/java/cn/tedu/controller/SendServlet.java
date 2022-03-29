package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");
        //获取参数
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        String categoryId = request.getParameter("categoryId");
        System.out.println(title+":"+author+":"+intro+":"+categoryId);
        //接收上传的文件
        Part part = request.getPart("file");
        //得到上传文件的后缀名
        //得到上传文件的描述信息
        String info = part.getHeader("content-disposition");
        System.out.println(info);
        //截取后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //得到唯一的文件名
        String fileName = UUID.randomUUID()+suffix;
        System.out.println(fileName);
        //得到和日期相关的文件路径
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd/");
        //创建当前时间的日期对象
        Date date = new Date();
        //得到文件夹路径的一部分
        String subPath = "images/"+f.format(date);
        System.out.println(subPath);
        //得到Tomcat管辖的完整路径
        String path = request.getServletContext().getRealPath(subPath);
        System.out.println(path);
        //创建文件夹
        new File(path).mkdirs();
        //把上传的文件上传到文件夹里面
        part.write(path+fileName);

        //把作品的信息封装到对象中
        Product product = new Product(0,title,author,intro,subPath+fileName,0,0,System.currentTimeMillis(),Integer.parseInt(categoryId));
        //创建Dao 并调用insert方法
        ProductDao dao  =new ProductDao();
        dao.insert(product);

        //重定向到/home首页
        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
