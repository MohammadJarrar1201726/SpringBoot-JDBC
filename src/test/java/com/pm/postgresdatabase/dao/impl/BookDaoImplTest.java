package com.pm.postgresdatabase.dao.impl;


import com.pm.postgresdatabase.TestDataUtil;
import com.pm.postgresdatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDao;

    @Test
    public void testThatCreateBookGenerateCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        bookDao.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn , title, author_id) VALUES (? , ? , ?)"),
                eq("1") , eq("FirstBook") , eq(1L)
        );
    }


    @Test
    public void testThatFindOneBookGeneratesTheCorrectSql(){
        bookDao.findOne("1");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ?" ),
                ArgumentMatchers.<BookDaoImpl.BookMapper>any(),
                eq("1")
        );

    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        bookDao.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books" ),
                ArgumentMatchers.<BookDaoImpl.BookMapper>any()
        );

    }

    @Test
    public void testThatUpdateGenerateTheCorrectSql(){

        Book book = TestDataUtil.createTestBook();
        bookDao.update("1" , book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ? , title = ? , author_id = ? WHERE isbn = ? " ,
                "1" , "FirstBook" , 1L , "1"
        );
    }
    @Test
    public void testThatDeleteGenerateTheCorrectSql(){
        bookDao.delete("1");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?" ,
                "1"
        );

    }
}
