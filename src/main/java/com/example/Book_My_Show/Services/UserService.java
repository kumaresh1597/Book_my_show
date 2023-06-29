package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserDto addUserDto) {
        User newUser = UserTransformer.convertUserDtoToEntity(addUserDto);

        userRepository.save(newUser);
        return "User added successfully";
    }

    public UserResponseDto getOldestUser() {
        List<User> userList = userRepository.findAll();
        int maxAge = 0;
        User userAns = null;
        for(User u : userList){
            if(u.getAge() > maxAge){
                maxAge = u.getAge();
                userAns = u;
            }
        }
        UserResponseDto userResponseDto = UserTransformer.convertUserEntityToUserDTO(userAns);

        return userResponseDto;
    }

    public List<UserResponseDto> getUsersGreaterThanAge(Integer age) {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> userList = userRepository.findUsersAgeGreater(age);
        for(User user : userList){
            UserResponseDto userResponseDto = UserTransformer.convertUserEntityToUserDTO(user);
            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("Success");
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }
}
