package com.pm.postgresdatabase.dao.impl;

import com.pm.postgresdatabase.TestDataUtil;
import com.pm.postgresdatabase.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGenerateCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id , name , age) VALUES (? , ? , ? )"),
                eq(1L) , eq("Mohammad Jarrar"), eq(23)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){

        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id , name, age FROM authors WHERE id = ? LIMIT 1")
                , ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
                , eq(1L)
        );
    }

    @Test
    public void testThatFindManyGenerateCorrectSql(){

        underTest.findMany();
        verify(jdbcTemplate).query(
                eq("SELECT id, name , age FROM authors")
                , ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );

    }

    @Test
    public void testThatUpdateGenerateCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(author.getId()  , author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ? , name = ? , age = ? WHERE id = ?",
                1L , "Mohammad Jarrar" , 23  , 1L

        );
    }

    @Test
    public void testThatDeleteGenerateCorrectSql(){
        underTest.delete( 1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?" , 1L
        );

    }
}
