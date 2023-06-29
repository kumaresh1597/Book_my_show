package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from users where age >= :givenAge ;",nativeQuery = true)
    List<User> findUsersAgeGreater(Integer givenAge);
}
