package com.wangpeng.service;

import com.wangpeng.pojo.Book;
import com.wangpeng.pojo.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);
    public void deleteBookById(int id);
    public void updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBook();

    public Page page(int pageNum, int pageSize);

    Page pageByPrice(int pageNum, int pageSize, int min, int max);
}
