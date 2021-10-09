package com.wangpeng.web;

import com.google.gson.Gson;
import com.wangpeng.pojo.Book;
import com.wangpeng.pojo.Cart;
import com.wangpeng.pojo.CartItem;
import com.wangpeng.service.BookService;
import com.wangpeng.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    /**
     * 添加购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取pageNum
        int pageNum;
        if(req.getParameter("pageNum") == null) pageNum = 1;
        else pageNum = Integer.parseInt(req.getParameter("pageNum"));
        //获取id信息
        int id = Integer.parseInt(req.getParameter("id"));
        //根据id信息获得图书信息
        Book book = bookService.queryBookById(id);
        //获得cart对象，先看看Session里有没有cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //添加图书到购物车中
        cart.addItem(new CartItem(id, book.getName(),1, book.getPrice(),book.getPrice()));
        req.getSession().setAttribute("lastName",book.getName());
        //重定向回到原来的页面
        resp.sendRedirect(req.getContextPath()
                + "/client/bookServlet?action=page&pageNum=" + pageNum);
    }

    protected void ajaxAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id信息
        int id = Integer.parseInt(req.getParameter("id"));
        //根据id信息获得图书信息
        Book book = bookService.queryBookById(id);
        //获得cart对象，先看看Session里有没有cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //添加图书到购物车中
        cart.addItem(new CartItem(id, book.getName(),1, book.getPrice(),book.getPrice()));
        //存map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("count",cart.getCount());
        map.put("lastName",book.getName());
        //转jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        //发送到客户端
        resp.getWriter().write(jsonString);
    }

    /**
     * 更新数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取信息
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        //获取Session中的cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //更新图书数量
        cart.updateItem(id,count);
        //重定向回到原来的页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        //获取Session域中的cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //调用cart对象的delete方法
        cart.delete(id);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session域中的cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //调用cart对象的delete方法
        cart.clear();
        //重定向
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }
}
