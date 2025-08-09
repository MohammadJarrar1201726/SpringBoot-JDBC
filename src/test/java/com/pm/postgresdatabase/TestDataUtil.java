package com.pm.postgresdatabase;

import com.pm.postgresdatabase.domain.Author;
import com.pm.postgresdatabase.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {

    }
    public static Author createTestAuthor(){
        return  Author.builder()
                .id(1L)
                .name("Mohammad Jarrar")
                .age(23)
                .build();
    }
    public static Author createTestAuthorB(){
        return  Author.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(24)
                .build();
    }
    public static Author createTestAuthorC(){
        return  Author.builder()
                .id(3L)
                .name("Jess A Casey")
                .age(22)
                .build();
    }



    public static Book createTestBook(){
        return  Book.builder()
                .isbn("1")
                .title("FirstBook")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookB(){
        return  Book.builder()
                .isbn("2")
                .title("SecondBook")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookC(){
        return  Book.builder()
                .isbn("3")
                .title("ThirdBook")
                .authorId(1L)
                .build();
    }

}
