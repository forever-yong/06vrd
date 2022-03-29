package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("详情页面id:"+id);
        //创建Dao  通过id查询作品详情
        ProductDao dao = new ProductDao();

        //点赞之前判断session中是否记录了这个作品
        HttpSession session = request.getSession();
        String viewId = (String) session.getAttribute("view"+id);
        if(viewId==null){
            //让浏览量加一
            dao.viewById(id);
            //把浏览过的作品id保存到session中
            session.setAttribute("view"+id,id);
        }

        Product product = dao.findById(id);

        Context context = new Context();
        context.setVariable("product",product);

        //得到登录的用户对象
        User user = (User) request.getSession().getAttribute("user");
        context.setVariable("user",user);
        //得到分类信息
        CategoryDao cDao = new CategoryDao();
        context.setVariable("cList",cDao.findAll());
        //浏览最多和最受欢迎
        context.setVariable("vList",dao.findList("view"));
        context.setVariable("lList",dao.findList("like"));


        ThUtils.print("detail.html",context,response);
    }
}
