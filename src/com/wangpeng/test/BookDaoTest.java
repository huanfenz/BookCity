package com.wangpeng.test;

import com.wangpeng.dao.BookDao;
import com.wangpeng.dao.impl.BookDaoImpl;
import com.wangpeng.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "狂人日记", "周树人", new BigDecimal(21.00),
                55, 45, null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "狂人日记", "周树人", new BigDecimal(21.00),
                55, 45, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookDao.queryBook();
        for(Book book : books) {
            System.out.println(book);
        }
    }
}