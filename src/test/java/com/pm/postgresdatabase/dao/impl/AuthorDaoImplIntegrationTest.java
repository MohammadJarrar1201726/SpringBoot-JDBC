package com.pm.postgresdatabase.dao.impl;

import com.pm.postgresdatabase.TestDataUtil;
import com.pm.postgresdatabase.dao.AuthorDao;
import com.pm.postgresdatabase.domain.Author;
import org.h2.store.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // clean down the database for each /test/ begin of class
public class AuthorDaoImplIntegrationTest {

    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author= TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent(); // actually exist in database
        assertThat(result.get()).isEqualTo(author); // This is the Recalled One
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        List<Author> authors = List.of(TestDataUtil.createTestAuthor()
                , TestDataUtil.createTestAuthorB(),
                TestDataUtil.createTestAuthorC());

        for(Author author: authors){
            underTest.create(author);
        }

        List<Author> result = underTest.findMany();

        assertThat(result).isNotEmpty();
        assertThat(result).containsAll(authors);



    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author author= TestDataUtil.createTestAuthor();
        underTest.create(author);
        author.setName("Updated");
        underTest.update(author.getId(), author);
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();

        underTest.delete(author.getId());
        result = underTest.findOne(author.getId());
        assertThat(result).isEmpty();
    }

}
