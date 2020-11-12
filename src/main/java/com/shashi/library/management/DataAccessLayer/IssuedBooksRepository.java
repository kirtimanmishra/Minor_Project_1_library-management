package com.shashi.library.management.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssuedBooksRepository extends JpaRepository<IssuedBooks,Integer>{

//    @Query("select b from IssuedBooks b where b.user_id=:Integer.parseInt(userId)")
//    List<IssuedBooks> findIssuedBooksByUser(@Param("userId") String userId);

    @Query("select b from IssuedBooks b where b.user_id=:userId")
    List<IssuedBooks> findIssuedBooksByUser(@Param("userId") int userId);
}
