package com.pm.postgresdatabase.dao;

import com.pm.postgresdatabase.domain.Author;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(long id);
    List<Author> findMany();
    void update(long id , Author author);
    void delete(long id);
}
