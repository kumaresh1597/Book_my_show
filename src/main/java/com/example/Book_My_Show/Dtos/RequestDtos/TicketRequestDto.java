package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDto {
    private int userId;
    private int showId;
    private List<String> requestedSeats;
    private boolean isFoodAttach;
}
