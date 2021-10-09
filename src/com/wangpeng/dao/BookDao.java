package com.wangpeng.dao;

import com.wangpeng.pojo.Book;

import java.util.List;

public interface BookDao {

    /**
     * 根据pojo添加图书
     * @param book
     * @return 影响的行数
     */
    public int addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return 影响的行数
     */
    public int deleteBookById(int id);

    /**
     * 根据pojo更新图书
     * @param book
     * @return 影响的行数
     */
    public int updateBook(Book book);

    /**
     * 根据id查询图书
     * @param id
     * @return 图书的pojo
     */
    public Book queryBookById(int id);

    /**
     * 查询数据库中所有的图书
     * @return 图书pojo的list
     */
    public List<Book> queryBook();

    public int queryCount();

    public List<Book> queryCurPage(int begin, int pageSize);

    int queryCountByPrice(int min, int max);

    List<Book> queryCurPageByPrice(int begin, int pageSize, int min, int max);
}
