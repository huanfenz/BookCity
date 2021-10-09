package com.wangpeng.dao.impl;

import com.wangpeng.dao.BookDao;
import com.wangpeng.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(id, name, author, price, sales, stock, imgPath) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return update(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "DELETE FROM t_book WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book SET id = ?, name = ?, author = ?, price = ?, sales = ?, stock = ?, imgPath = ? WHERE id = ?";
        return update(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "SELECT id, name, author, price, sales, stock, imgPath FROM t_book WHERE id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBook() {
        String sql = "SELECT id, name, author, price, sales, stock, imgPath FROM t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public int queryCount() {
        String sql = "SELECT COUNT(*) FROM t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryCurPage(int begin, int pageSize) {
        String sql = "SELECT id, name, author, price, sales, stock, imgPath FROM t_book LIMIT ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public int queryCountByPrice(int min, int max) {
        String sql = "SELECT COUNT(*) FROM t_book WHERE price BETWEEN ? and ?";
        Number number = (Number) queryForSingleValue(sql, min, max);
        return number.intValue();
    }

    @Override
    public List<Book> queryCurPageByPrice(int begin, int pageSize, int min, int max) {
        String sql = "SELECT id, name, author, price, sales, stock, imgPath " +
                "FROM t_book WHERE price BETWEEN ? and ? LIMIT ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
