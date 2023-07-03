package com.example.Book_My_Show.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private String statusCode;
    private String statusMessage;

    private String movieName;
    private String theaterName;
    private Date showDate;
    private LocalTime showTime;
    private int totalPrice;
    private String bookedSeats;
}
