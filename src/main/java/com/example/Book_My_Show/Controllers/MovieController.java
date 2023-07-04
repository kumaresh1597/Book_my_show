package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.MovieResponseDto;
import com.example.Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody MovieEntryDto movieEntryDto){

        return movieService.addMovie(movieEntryDto);
    }

    @GetMapping("/getMovieName")
    public String getMovieById(@RequestParam("movieId") int movieId){

        return movieService.getMovieById(movieId);
    }
    @GetMapping("/getMovieWithMaxNumOfShows")
    public MovieResponseDto getMovieWithMaxNumOfShows(){
        return movieService.getMovieWithMaxNoOfShows();
    }

    @GetMapping("/getTotalRevenueOfAMovie")
    public int getTotalRevenueOfAMovie(@RequestParam("movieId") int movieId){
        return movieService.getTotalRevenue(movieId);
    }
}
