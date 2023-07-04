package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TheaterTimeRequestDto {
    private LocalTime time;
}
