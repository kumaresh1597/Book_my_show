package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class AssociateShowDto {
    private int showId;
    private int classicPrice;
    private int premiumPrice;
}
