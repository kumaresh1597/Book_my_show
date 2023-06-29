package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Models.User;

public class UserTransformer {
    public static User convertUserDtoToEntity(AddUserDto userDto){
        User newUser = User.builder().name(userDto.getName()).age(userDto.getAge()).mobile(userDto.getMobNo())
                        .email(userDto.getEmail()).build();
        return newUser;
    }

    public static UserResponseDto convertUserEntityToUserDTO(User userAns) {
        UserResponseDto userResponseDto = UserResponseDto.builder().name(userAns.getName()).age(userAns.getAge())
                .mobile(userAns.getMobile()).email(userAns.getEmail()).build();
        return userResponseDto;
    }
}
