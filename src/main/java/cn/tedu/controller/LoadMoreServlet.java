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

@WebServlet(name = "LoadMoreServlet",urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到跳过的条数
        String count = request.getParameter("count");
        //查询加载的新的作品信息
        ProductDao dao = new ProductDao();
        List<Product> list = dao.loadMore(count);
        //把集合对象转成Jason格式的字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.close();
    }
}
