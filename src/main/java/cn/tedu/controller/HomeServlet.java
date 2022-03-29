package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
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
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传递过来的参数
        //分类id
        String id = request.getParameter("id");
        System.out.println("首页分类id:"+id);
        String keyword = request.getParameter("keyword");
        System.out.println("首页搜索:"+keyword);
        CategoryDao cDao = new CategoryDao();
        List<Category> cList = cDao.findAll();
        Context context = new Context();
        context.setVariable("cList",cList);

        //取出保存到Session对象里面的user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //把取出的用户对象保存到容器中  如果登录过user有值 没登录过user为null;
        context.setVariable("user",user);
        if(user!=null){
            System.out.println("登陆过");
        }else{
            System.out.println("没登录过");
        }

        //查询轮播图数据
        BannerDao bDao = new BannerDao();
        List<Banner> bList = bDao.findAll();
        context.setVariable("bList",bList);

        //查询作品信息
        ProductDao pDao = new ProductDao();
        List<Product> pList =null;
        if(id!=null){//如果分类id有值说明需要查询这个分类的作品
            pList = pDao.findByCategoryId(id);
        }else if (keyword!=null){//关键字有值  执行搜索
            pList = pDao.findByKeyword(keyword);
        }else{//查询所有作品
            pList = pDao.findAll();
        }

        context.setVariable("pList",pList);
        System.out.println("作品信息"+pList);

        //查询浏览最多作品信息
        List<Product> vList = pDao.findList("view");
        context.setVariable("vList",vList);

        //查询喜欢最多作品信息
        List<Product> lList = pDao.findList("like");
        context.setVariable("lList",lList);
        ThUtils.print("home.html",context,response);

    }
}
