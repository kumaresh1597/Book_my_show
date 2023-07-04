package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "select sum(price) from tickets join shows on tickets.show_show_id = shows.show_id where shows.movie_movie_id = :movieId ;",nativeQuery = true)
    Integer getRevenueOfAMovie(int movieId);
}
