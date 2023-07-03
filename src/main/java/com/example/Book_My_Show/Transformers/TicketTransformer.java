package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Models.Ticket;

import java.util.List;

public class TicketTransformer {

    public static String convertListToStrings(List<String> requestedSeats){
            String result = "";
            for(String s : requestedSeats){
                result += s+",";
            }
            return result;
    }

    public static TicketResponseDto createTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder().bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getShow().getMovie().getMovieName())
                .showDate(ticket.getShow().getDate())
                .showTime(ticket.getShow().getTime())
                .theaterName(ticket.getShow().getTheater().getName())
                .totalPrice(ticket.getPrice()).build();

        return ticketResponseDto;
    }
}
