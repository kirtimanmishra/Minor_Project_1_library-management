package com.Kirti.library.management.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("select u from User u where u.id=:userId")
	User findById(@Param("userId") int userId);

//	@Query("select b from IssuedBooks b where b.user_id=:userId")
//	List<IssuedBooks> findIssuedBooksByUser(@Param("userId") int userId);
}
