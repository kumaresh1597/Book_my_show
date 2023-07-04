package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

import java.util.Date;
@Data
public class ShowsOnDateDto {
    private int movieId;
    private int theaterId;
    private Date date;
}
