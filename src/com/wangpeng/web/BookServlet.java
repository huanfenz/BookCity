package com.wangpeng.web;

import com.wangpeng.pojo.Book;
import com.wangpeng.pojo.Page;
import com.wangpeng.service.BookService;
import com.wangpeng.service.impl.BookServiceImpl;
import com.wangpeng.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    /**
     * 显示列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据
        List<Book> books = bookService.queryBook();
        //保存图书信息到request域中
        req.setAttribute("books",books);
        //请求转发到book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNum,pageSize;
        if(req.getParameter("pageNum") == null) pageNum = 1;
        else pageNum = Integer.parseInt(req.getParameter("pageNum"));

        if(req.getParameter("pageSize") == null) pageSize = 4;
        else pageSize = Integer.parseInt(req.getParameter("pageSize"));

        Page page = bookService.page(pageNum, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //保存到req域中
        req.setAttribute("page", page);
        //请求转发到图书管理页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNum = Integer.parseInt(req.getParameter("pageNum")) + 1;
        //获取参数，使用WebUtils注入pojo
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //添加数据库
        bookService.addBook(book);
        //重定向到BookServlet
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum=" + pageNum);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //操作数据库
        bookService.deleteBookById(id);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum="+req.getParameter("pageNum"));
    }

    protected void getData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //查询结果
        Book book = bookService.queryBookById(id);
        //送req域
        req.setAttribute("book", book);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //操作数据库
        bookService.updateBook(book);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNum="+req.getParameter("pageNum"));
    }
}
