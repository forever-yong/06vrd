package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet",urlPatterns = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BannerDao dao = new BannerDao();
        //查询轮播图的路径
        Banner banner = dao.findById(id);
        System.out.println(banner);
        String filePath = request.getServletContext().getRealPath(banner.getImgUrl());
        new File(filePath).delete();//从文件系统删除文件

        dao.deleteById(id);
        //重定向回到修改轮播图页面
        response.sendRedirect("/showbanner");
    }
}
