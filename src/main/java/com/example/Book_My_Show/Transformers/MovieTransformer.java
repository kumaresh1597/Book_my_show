package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.MovieResponseDto;
import com.example.Book_My_Show.Models.Movie;

public class MovieTransformer {

    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto){
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .genre(movieEntryDto.getGenre()).duration(movieEntryDto.getDuration())
                .language(movieEntryDto.getLanguage()).rating(movieEntryDto.getRating())
                .releaseDate(movieEntryDto.getReleaseDate()).build();

        return movie;
    }

    public static MovieResponseDto convertEntityToDto(Movie maxMovie) {
        MovieResponseDto movieResponseDto = MovieResponseDto.builder().movieName(maxMovie.getMovieName())
                .genre(maxMovie.getGenre())
                .releaseDate(maxMovie.getReleaseDate())
                .duration(maxMovie.getDuration())
                .language(maxMovie.getLanguage())
                .rating(maxMovie.getRating()).build();

        return movieResponseDto;
    }
}
