package com.Kirti.library.management.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>{
    @Query("select b from Book b where b.author=:author")
    List<Book> findBooksByAuthor(@Param("author") String author);

    @Query("select b from Book b where b.subject=:subject")
    List<Book> findBooksBySubject(@Param("subject") String subject);
}
