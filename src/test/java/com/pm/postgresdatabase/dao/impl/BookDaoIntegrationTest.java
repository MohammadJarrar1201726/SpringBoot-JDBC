package com.pm.postgresdatabase.dao.impl;

import ch.qos.logback.core.testUtil.TeeOutputStream;
import com.pm.postgresdatabase.TestDataUtil;
import com.pm.postgresdatabase.domain.Author;
import com.pm.postgresdatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode=  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoIntegrationTest {

    private final BookDaoImpl underTest;
    private final AuthorDaoImpl authorDao;

    @Autowired
    public BookDaoIntegrationTest(BookDaoImpl underTest , AuthorDaoImpl authorDao) {
        this.underTest= underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Book book = TestDataUtil.createTestBook();

        Author author = TestDataUtil.createTestAuthor();

        authorDao.create(author);

        underTest.create(book);

        Optional<Book>result =underTest.findOne(book.getIsbn());

        //Assertion
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        //create the Author (To not got foreign key Exception)
        Author author= TestDataUtil.createTestAuthor();
        authorDao.create(author);


        //Create Multiple Books
        List<Book> books = List.of(
                TestDataUtil.createTestBook(),
                TestDataUtil.createTestBookB(),
                TestDataUtil.createTestBookC()
        );
        for(Book book : books){
            underTest.create(book);
        }




        List<Book> result = underTest.findAll();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(books.get(0));
        assertThat(result).containsAll(books);

    }

    @Test
    public void testThatABookCanBeUpdated(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
        underTest.create(book);
        book.setTitle("FirstBookUpdated");
        underTest.update(book.getIsbn() , book);

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatBookCanBeDeleted(){
        Book book = TestDataUtil.createTestBook();
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
        underTest.create(book);

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();

        underTest.delete(book.getIsbn());

        result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }

}
