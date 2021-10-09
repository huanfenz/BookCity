package com.wangpeng.service.impl;

import com.wangpeng.dao.BookDao;
import com.wangpeng.dao.impl.BookDaoImpl;
import com.wangpeng.pojo.Book;
import com.wangpeng.pojo.Page;
import com.wangpeng.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBook() {
        return bookDao.queryBook();
    }

    @Override
    public Page page(int pageNum, int pageSize) {   //页码，每页大小
        int itemsCount = bookDao.queryCount();    //获取总记录数
        int pageTotal = itemsCount / pageSize;  //获取总页数
        if(itemsCount % pageSize != 0) pageTotal++; //有余数，页数+1

        // 边界处理
        if(pageNum < 1) pageNum = 1;
        if(pageNum > pageTotal)pageNum = pageTotal;

        //获取起始索引
        int begin = (pageNum - 1) * pageSize;
        //获取当前页的数据
        List<Book> books = bookDao.queryCurPage(begin, pageSize);

        Page page = new Page(pageNum,pageTotal,pageSize,itemsCount,books,null);
        return page;
    }

    @Override
    public Page pageByPrice(int pageNum, int pageSize, int min, int max) {
        int itemsCount = bookDao.queryCountByPrice(min, max);    //获取总记录数
        int pageTotal = itemsCount / pageSize;  //获取总页数
        if(itemsCount % pageSize != 0) pageTotal++; //有余数，页数+1

        // 边界处理
        if(pageNum < 1) pageNum = 1;
        if(pageNum > pageTotal)pageNum = pageTotal;

        //获取起始索引
        int begin = (pageNum - 1) * pageSize;
        //获取当前页的数据
        List<Book> books = bookDao.queryCurPageByPrice(begin, pageSize, min, max);

        Page page = new Page(pageNum,pageTotal,pageSize,itemsCount,books,null);
        return page;
    }
}
