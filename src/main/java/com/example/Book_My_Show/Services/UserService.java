package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Exceptions.TicketNotAvailableException;
import com.example.Book_My_Show.Exceptions.UserNotFoundException;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Transformers.TicketTransformer;
import com.example.Book_My_Show.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

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

    public List<TicketResponseDto> getAllTickets(int userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not found");

        User user = userOptional.get();

        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for(Ticket ticket : user.getTicketList()){
            TicketResponseDto trd =  TicketTransformer.createTicketResponseDto(ticket);
            ticketResponseDtoList.add(trd);
        }

        return ticketResponseDtoList;
    }

    public String cancelTicket(int ticketId) throws TicketNotAvailableException {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()) throw new TicketNotAvailableException("Ticket Id is wrong");

        ticketRepository.deleteById(ticketId);

        return "Tickets cancelled successfully";
    }
}
