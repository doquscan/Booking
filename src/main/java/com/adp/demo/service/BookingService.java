package com.adp.demo.service;

import com.adp.demo.dto.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    /**
     * Books a conference room if there are no overlapping reservations.
     * @param startTime The start time of the booking.
     * @param durationMinutes The duration of the booking in minutes. Defaults to 30 if not provided.
     * @return A confirmation message or an error message if there is an overlap.
     */
    public String bookConferenceRoom(LocalDateTime startTime, Integer durationMinutes) {
        // Default duration to 30 minutes if not provided
        if (durationMinutes == null) {
            durationMinutes = 30;
        }

        // Calculate end time of the booking
        LocalDateTime endTime = startTime.plusMinutes(durationMinutes);

        // Create a new booking object
        Booking newBooking = new Booking(startTime, endTime);

        // Check for overlapping bookings
        for (Booking booking : bookings) {
            if (booking.overlaps(newBooking)) {
                return "Room is already booked during this time.";
            }
        }

        // Add the new booking if there is no overlap
        bookings.add(newBooking);
        return "Conference room booked from " + startTime + " to " + endTime + ".";
    }

    /**
     * Returns a list of all current bookings.
     * @return List of bookings.
     */
    public List<Booking> getAllBookings() {
        return bookings;
    }
}