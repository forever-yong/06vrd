package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListServlet",urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建Dao 并查询所有作品
        ProductDao dao = new ProductDao();
        List<Product> list = dao.findAll();
        //把得到的集合转成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json  =objectMapper.writeValueAsString(list);
        //把json字符串返回给客户端
        //text/html表示返回的数据是文本或标签
        //application/json告诉客户端返回的是json对象  客户端接收到数据时不需要再进行转换
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw =  response.getWriter();
        pw.print(json);
        pw.close();
    }
}
