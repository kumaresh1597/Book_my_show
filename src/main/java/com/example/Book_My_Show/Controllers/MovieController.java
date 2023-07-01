package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody MovieEntryDto movieEntryDto){
        return movieService.addMovie(movieEntryDto);
    }
}
