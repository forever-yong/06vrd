package cn.tedu.filter;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsend","/showbanner","/delete"})
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //在此方法中写多个Servlet中重用的代码
        //因为需要用到子类类型中的方法  所以进行类型强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取session中的用户对象
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){//没登录
            System.out.println("没登录,请先登录!!!");
            //重定向到登录页面
            response.sendRedirect("/login");
        }else{//登陆过
            System.out.println("已登录,放行!!!");
            //放行  允许执行后面的Servlet
            chain.doFilter(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
