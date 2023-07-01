package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.ShowEntryDto;
import com.example.Book_My_Show.Models.Show;

public class ShowTransformer {

    public static Show convertDtoToEntity(ShowEntryDto showEntryDto){

        Show show = Show.builder().date(showEntryDto.getDate()).time(showEntryDto.getTime()).build();
        return show;
    }
}
