package client;

import cinemaObjects.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookings implements ClientCommand {

    private final List<Booking> bookings;

    public ReturnBookings(List<Booking> bookingsByPhoneNumber) { this.bookings = bookingsByPhoneNumber; }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setBookings(this.bookings);
    }
}