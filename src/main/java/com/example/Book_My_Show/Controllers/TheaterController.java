package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.TheaterEntryDto;
import com.example.Book_My_Show.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.example.Book_My_Show.Dtos.RequestDtos.TheaterTimeRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TheaterResponseDto;
import com.example.Book_My_Show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    TheaterService theaterService;
    @PostMapping("/addTheater")
    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        String response = theaterService.addTheater(theaterEntryDto);
        return response;
    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody TheaterSeatEntryDto theaterSeatEntryDto){
        String response = theaterService.addTheaterSeats(theaterSeatEntryDto);
        return response;
    }
    @GetMapping("/getTheatersAtParticularTime")
    public List<TheaterResponseDto> getTheatersAtParticularTime(@RequestBody TheaterTimeRequestDto theaterTimeRequestDto){
        return theaterService.getTheatersAtParticularTime(theaterTimeRequestDto);
    }
}
