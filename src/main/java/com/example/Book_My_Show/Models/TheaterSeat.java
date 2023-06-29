package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;


@Entity
@Table(name = "theater_Seats")
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theater theater;
}
