package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.RequestDtos.AssociateShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowEntryDto;
import com.example.Book_My_Show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
