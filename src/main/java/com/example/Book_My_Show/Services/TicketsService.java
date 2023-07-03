package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Dtos.RequestDtos.TicketRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TicketNotAvailableException;
import com.example.Book_My_Show.Exceptions.UserNotFoundException;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.ShowSeat;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender emailSender;
    public TicketResponseDto bookTickets(TicketRequestDto ticketRequestDto) throws ShowNotFoundException, UserNotFoundException, TicketNotAvailableException {
        Optional<User> userOptional = userRepository.findById(ticketRequestDto.getUserId());
        if(userOptional.isEmpty()) throw new UserNotFoundException("Invalid User Id");

        Optional<Show> showOptional = showRepository.findById(ticketRequestDto.getShowId());
        if(showOptional.isEmpty()) throw new ShowNotFoundException("Show Id is wrong");

        Show show = showOptional.get();
        User user = userOptional.get();

        boolean isValid = vaidatingRequestedSeats(show,ticketRequestDto);
        if(isValid == false) throw new TicketNotAvailableException("Tickets not available");



        int price = calcuateTicketPrice(show,ticketRequestDto);

        String bookedSeats = TicketTransformer.convertListToStrings(ticketRequestDto.getRequestedSeats());

        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setBookedSeats(bookedSeats);

        ticket.setShow(show);
        ticket.setUser(user);

        ticket = ticketRepository.save(ticket);

        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);

        showRepository.save(show);
        userRepository.save(user);

        TicketResponseDto ticketResponseDto = TicketTransformer.createTicketResponseDto(ticket);

        int noOfTickets = ticketRequestDto.getRequestedSeats().size();

        String body = "Hi !!"+user.getName()+"\n"+
                "Your booking is confirmed!\n"+
                "Booking Id: "+ticket.getTicketId()+"\n"+
                "Movie name: "+ticketResponseDto.getMovieName()+" ("+ticket.getShow().getMovie().getLanguage()+")\n"+
                "Show Date & time: "+ticketResponseDto.getShowTime()+" | "+ticketResponseDto.getShowDate()+"\n"+
                "Theater Name: "+ticketResponseDto.getTheaterName()+"\n"+
                "Location: "+ticket.getShow().getTheater().getLocation()+"\n"+
                "\n"+
                "No of tickets: "+noOfTickets+"\n"+
                "Confirmed seats: "+ticketResponseDto.getBookedSeats()+"\n"+
                "\n"+
                "Ticket Amount: "+ticketResponseDto.getTotalPrice()+" ("+noOfTickets+" tickets)"+"\n"+
                "\n"+
                "Thank you for using book my show, its our pleasure to serve you \n"+
                "\n"+
                "Enjoy your Movie!!";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("forspring587@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Ticket Booking successful!!");
        mailMessage.setText(body);

        emailSender.send(mailMessage);

        return ticketResponseDto;
    }



    private int calcuateTicketPrice(Show show, TicketRequestDto ticketRequestDto) {
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int price = 0;
      //  System.out.println(ticketRequestDto.getIsFoodAttach());
        for(ShowSeat showSeat: showSeatList){
            if(ticketRequestDto.getRequestedSeats().contains(showSeat.getSeatNo())){
                showSeat.setAvailable(false);
                price += showSeat.getPrice();
                if(ticketRequestDto.isFoodAttach()){
                    showSeat.setFoodAttached(true);
                    price += showSeat.getFoodPrice();
                }
            }
        }
        return price;
    }

    private boolean vaidatingRequestedSeats(Show show, TicketRequestDto ticketRequestDto) {
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat: showSeatList){
            if(ticketRequestDto.getRequestedSeats().contains(showSeat.getSeatNo())){
                if(showSeat.isAvailable() == false){
                    return false;
                }
            }
        }
        return true;
    }
}
