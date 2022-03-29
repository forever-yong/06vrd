package cn.tedu.controller;

import cn.tedu.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikeServlet",urlPatterns = "/like")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("点赞id:"+id);
        //点赞之前判断session中是否记录了这个作品
        HttpSession session = request.getSession();
        String likeId = (String) session.getAttribute("like"+id);
        if(likeId==null){
            //创建dao并调用点赞方法
            ProductDao dao = new ProductDao();
            dao.likeById(id);
            //把点过赞的作品id保存到session中
            session.setAttribute("like"+id,id);

        }

        //重定向到详情页面  显示的仍然是当前作品信息
        response.sendRedirect("/detail?id="+id);
    }
}
