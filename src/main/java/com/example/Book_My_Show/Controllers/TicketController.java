package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.TicketRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TicketNotAvailableException;
import com.example.Book_My_Show.Exceptions.UserNotFoundException;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketsService ticketsService;
    @PostMapping("/bookTickets")
    public TicketResponseDto bookTickets(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto ticketResponseDto = ticketsService.bookTickets(ticketRequestDto);
            ticketResponseDto.setStatusCode("200");
            ticketResponseDto.setStatusMessage("Success");
            return ticketResponseDto;
        } catch (Exception e) {
          TicketResponseDto ticketResponseDto = new TicketResponseDto();
          ticketResponseDto.setStatusCode("500");
          ticketResponseDto.setStatusMessage("Failure");
          return ticketResponseDto;
        }
    }
}
