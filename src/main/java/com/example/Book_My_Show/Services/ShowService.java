package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.AssociateShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Exceptions.MovieNotFoundException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TheaterNotFoundException;
import com.example.Book_My_Show.Models.*;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.ShowSeatRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    public String addShow(ShowEntryDto showEntryDto) throws MovieNotFoundException, TheaterNotFoundException {
        Show show = ShowTransformer.convertDtoToEntity(showEntryDto);

        Optional<Movie> movieOptional = movieRepository.findById(showEntryDto.getMovieId());
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not Found");
        }
        Optional<Theater> theaterOptional = theaterRepository.findById(showEntryDto.getTheaterId());
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("Theater not Found");
        }

        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();

        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show added successfully with show id : "+show.getShowId();
    }

    public String associateShowSeats(AssociateShowDto associateShowDto) throws ShowNotFoundException {
        Optional<Show> showOptional = showRepository.findById(associateShowDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFoundException("Show id is wrong");
        }
        Show show = showOptional.get();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat seat : show.getTheater().getTheaterSeats()){

            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(seat.getSeatNo());
            showSeat.setSeatType(seat.getSeatType());
            if(seat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(associateShowDto.getClassicPrice());
            } else  {
                showSeat.setPrice(associateShowDto.getPremiumPrice());
            }
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);
            showSeat.setFoodPrice(associateShowDto.getFoodPrice());
            showSeat.setShow(show);

            showSeatList.add(showSeat);

        }

        showRepository.save(show);


        return "Show seats are successfully added to theater seats";
    }
}
