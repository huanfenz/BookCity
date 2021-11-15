package com.wangpeng.web;

import com.google.gson.Gson;
import com.wangpeng.pojo.User;
import com.wangpeng.service.UserService;
import com.wangpeng.service.impl.UserServiceImpl;
import com.wangpeng.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void ajaxCheckName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean flag = userService.existsUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("existsUsername",flag);

        Gson gson = new Gson();
        String json = gson.toJson(map);

        resp.getWriter().write(json);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        //调用登陆服务，验证账号密码
        if( user == null) {  //数据库中没数据
            System.out.println("登陆失败!");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {    //数据库中有数据，登陆成功，跳到登陆成功页面
            System.out.println("登陆成功！");
            //保存用户登陆的信息到Session域中
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除Session中的验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String vercode = req.getParameter("vercode");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //检查验证码是否正确 ===写死，要求验证码为 abcde
        if(vercode.equalsIgnoreCase(token)) {    //正确
            //检查用户名是否可用
            if(userService.existsUsername(username)) {  //用户名存在，则不可用，跳回注册页面
                System.out.println("用户名[" + username + "]已存在！");

                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } else {    //用户名不存在，则可用，跳到注册成功页面
                System.out.println("注册成功！");
                userService.registUser(new User(null, username, password, email));
                //保存用户登陆的信息到Session域中
                // req.getSession().setAttribute("user", user);
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        } else {    //不正确，跳回注册页面
            System.out.println("验证码[" + vercode +"]错误");
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session中用户登陆的信息
        req.getSession().invalidate();
        //重定向会到首页
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}
