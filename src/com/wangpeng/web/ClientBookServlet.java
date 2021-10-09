package com.wangpeng.web;

import com.wangpeng.pojo.Page;
import com.wangpeng.service.BookService;
import com.wangpeng.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNum,pageSize;
        //获取页码
        if(req.getParameter("pageNum") == null) pageNum = 1;
        else pageNum = Integer.parseInt(req.getParameter("pageNum"));
        //获取每页大小
        if(req.getParameter("pageSize") == null) pageSize = 4;
        else pageSize = Integer.parseInt(req.getParameter("pageSize"));

        Page page = bookService.page(pageNum, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //保存到req域中
        req.setAttribute("page", page);
        //请求转发client里的index
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNum,pageSize;
        //获取页码
        if(req.getParameter("pageNum") == null) pageNum = 1;
        else pageNum = Integer.parseInt(req.getParameter("pageNum"));
        //获取每页大小
        if(req.getParameter("pageSize") == null) pageSize = 4;
        else pageSize = Integer.parseInt(req.getParameter("pageSize"));

        //获取最小和最大价格
        int min, max;
        if(req.getParameter("min") == null) min = 0;
        else min = Integer.parseInt(req.getParameter("min"));
        if(req.getParameter("max") == null) max = Integer.MAX_VALUE;
        else max = Integer.parseInt(req.getParameter("max"));
        //获取页数据-
        Page page = bookService.pageByPrice(pageNum, pageSize, min, max);
        page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);
        //保存到req域中
        req.setAttribute("page", page);
        //请求转发client里的index
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
