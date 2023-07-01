package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowEntryDto {

    private Date date;
    private LocalTime time;
    private int movieId;
    private int theaterId;
}
