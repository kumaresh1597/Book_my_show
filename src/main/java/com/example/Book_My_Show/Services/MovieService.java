package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.MovieResponseDto;
import com.example.Book_My_Show.Models.Movie;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public String addMovie(MovieEntryDto movieEntryDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);

        return "Movie added successfully";
    }

    public String getMovieById(int movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getMovieName();
    }

    public MovieResponseDto getMovieWithMaxNoOfShows() {
        List<Movie> movieList = movieRepository.findAll();
        int maxShows = 0;
        Movie maxMovie = null;
        for(Movie movie : movieList){
            if(movie.getShowList().size() > maxShows){
                maxShows = movie.getShowList().size();
                maxMovie = movie;
            }
        }
        MovieResponseDto movieResponseDto = MovieTransformer.convertEntityToDto(maxMovie);

        return movieResponseDto;
    }

    public int getTotalRevenue(int movieId) {
        return ticketRepository.getRevenueOfAMovie(movieId);
    }
}
