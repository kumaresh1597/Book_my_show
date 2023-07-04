package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Show;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Integer> {
//    @Query(value = "select * from shows where movie_movie_id = :givenMovieId and theater_theater_id = :givenTheaterId and date = :givenDate ;")
//    List<Show> getAllShowsOnADay(Date givenDate, int givenMovieId, int givenTheaterId);
}
