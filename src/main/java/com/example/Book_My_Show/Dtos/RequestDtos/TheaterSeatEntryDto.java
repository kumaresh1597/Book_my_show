package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class TheaterSeatEntryDto {
    private String location;
    private int noOfColumns;
    private int noOfClassic;
    private int noOfPremium;
}
