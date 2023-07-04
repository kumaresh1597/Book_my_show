package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.AssociateShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowEntryDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowsOnDateDto;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;
    @PostMapping("/addShow")
    public String addShow(@RequestBody ShowEntryDto showEntryDto) {
        try{
            String response = showService.addShow(showEntryDto);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @PostMapping("/associateShowSeats")
    public String associateShowSeats(@RequestBody AssociateShowDto associateShowDto){
        try{
            String response = showService.associateShowSeats(associateShowDto);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getShowsOfParticularTheaterAndMovieOnGivenDate")
    public List<LocalTime> getshowsOfParticularTheaterandMovieOngivenDate(@RequestBody ShowsOnDateDto showsOnDateDto){
        try {
            List<LocalTime> showTimings = showService.getShowsOfParticularTheaterAndMovieOnGivenDate(showsOnDateDto);
            return showTimings;
        }catch (Exception e){
            System.out.println("not excecuted");
            throw new RuntimeException(e.getMessage());
        }
    }

}
