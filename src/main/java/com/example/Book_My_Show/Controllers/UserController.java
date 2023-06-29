package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
     @Autowired
    UserService userService;

     @PostMapping("/addUser")
     public String addUser(@RequestBody AddUserDto addUserDto){
         try{
         String response = userService.addUser(addUserDto);
         return response;
     } catch (Exception e) {
             return e.getMessage();
         }
     }

     @GetMapping("/getOldestUser")
     public UserResponseDto getOldestUser(){
         try{
             UserResponseDto userResponseDto = userService.getOldestUser();
             userResponseDto.setStatusCode("200");
             userResponseDto.setStatusMessage("Success");
             return userResponseDto;
         } catch (Exception e) {
             UserResponseDto userResponseDto = new UserResponseDto();
             userResponseDto.setStatusCode("500");
             userResponseDto.setStatusMessage("Failure");
             return userResponseDto;
         }
     }

     @GetMapping("/getUsersGreaterThanGivenAge")
     public List<UserResponseDto>  getUsersGreaterThanAge(@RequestParam("age") Integer age){

         List<UserResponseDto> usersList = userService.getUsersGreaterThanAge(age);
         return usersList;
     }
}
