package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class AddUserDto {
    private String name;
    private int age;
    private String mobNo;
    private String email;
}
