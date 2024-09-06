package com.adp.demo.controller;

import com.adp.demo.dto.Booking;
import com.adp.demo.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Create a REST API to Book a conference room.
-- Reservation defaults to 30 minutes if not provided,
-- Should avoid concurrent booking and overlapping bookings.
-- Assume only one conference room
-- for simplicity store the data in memory .
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    // Inject the BookingService using constructor-based dependency injection
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint to book a conference room.
     * @param startTimeStr The start time in string format.
     * @param durationMinutes Optional duration in minutes. Defaults to 30 if not provided.
     * @return A success or error message.
     */
    @PostMapping
    public String bookConferenceRoom(@RequestParam("startTime") String startTimeStr,
                                     @RequestParam(value = "durationMinutes", required = false) Integer durationMinutes) {
        try {
            // Convert the start time string to LocalDateTime
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr);

            // Call the service layer method to handle booking logic
            return bookingService.bookConferenceRoom(startTime, durationMinutes);
        } catch (DateTimeParseException e) {
            return "Invalid date-time format. Please use the format: yyyy-MM-dd'T'HH:mm";
        }
    }

    /**
     * Endpoint to get all bookings.
     * @return List of all current bookings.
     */
    @GetMapping
    public List<Booking> getBookings() {
        // Call the service layer method to retrieve all bookings
        return bookingService.getAllBookings();
    }
}
