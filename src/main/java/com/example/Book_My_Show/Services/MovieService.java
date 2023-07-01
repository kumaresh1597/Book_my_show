package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Models.Movie;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);

        return "Movie added successfully";
    }
}
