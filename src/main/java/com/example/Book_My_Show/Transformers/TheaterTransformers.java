package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.TheaterEntryDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TheaterResponseDto;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.Theater;

public class TheaterTransformers {

    public static Theater convertTheaterDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theater = Theater.builder().name(theaterEntryDto.getName()).location(theaterEntryDto.getLocation()).build();
        return theater;
    }

    public static TheaterResponseDto createTheaterResponseDto(Show show) {
        TheaterResponseDto theaterResponseDto = TheaterResponseDto.builder().theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .location(show.getTheater().getLocation())
                .date(show.getDate()).build();

        return theaterResponseDto;
    }
}
