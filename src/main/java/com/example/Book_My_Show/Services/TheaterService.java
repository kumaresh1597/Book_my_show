package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.TheaterEntryDto;
import com.example.Book_My_Show.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.example.Book_My_Show.Dtos.RequestDtos.TheaterTimeRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TheaterResponseDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.Theater;
import com.example.Book_My_Show.Models.TheaterSeat;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Transformers.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    private ShowRepository showRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) {
        Theater theater = TheaterTransformers.convertTheaterDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    public String addTheaterSeats(TheaterSeatEntryDto theaterSeatEntryDto) {

        int columns = theaterSeatEntryDto.getNoOfColumns();

        int noOfClassicSeats = theaterSeatEntryDto.getNoOfClassic();
        int noOfPremiumSeats = theaterSeatEntryDto.getNoOfPremium();

        String location = theaterSeatEntryDto.getLocation();

        Theater theater = theaterRepository.findByLocation(location);

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeats();




        int counter = 1;
        char ch = 'A';

        //this is done for the classic seats
        for(int count = 1;count<=noOfClassicSeats;count++){

            String seatNo = counter+""+ch;
           // seatNo = seatNo + ch;

            ch++;

            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }


            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater); //storing parent info in child
            theaterSeat.setSeatType(SeatType.CLASSIC);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            theaterSeatList.add(theaterSeat);
        }

        //Lets to the same for the premium seats
        for(int count=1;count<=noOfPremiumSeats;count++){

            String seatNo = counter+""+ch;
           // seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setSeatNo(seatNo);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity

            theaterSeatList.add(theaterSeat);
        }
        //We just need to save the parent : theater Entity
        //child will automatically get saved bcz of bidirectional mapping
        theaterRepository.save(theater);

        return "Theater Seats have been successfully added";
    }

    public List<TheaterResponseDto> getTheatersAtParticularTime(TheaterTimeRequestDto theaterTimeRequestDto) {
        List<Show> showList = showRepository.findAll();
        List<TheaterResponseDto> theaterResponseDtoList = new ArrayList<>();
        for(Show show : showList){
            if(show.getTime().equals(theaterTimeRequestDto.getTime())){
                theaterResponseDtoList.add(TheaterTransformers.createTheaterResponseDto(show));
            }
        }
        return theaterResponseDtoList;
    }
}
