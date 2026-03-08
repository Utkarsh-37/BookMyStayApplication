package com.bookmystay.booking;

import java.util.LinkedList;
import java.util.Queue;

public class BookingQueueService {

    private Queue<Reservation> bookingQueue = new LinkedList<>();

    private BookingService bookingService;

    public BookingQueueService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void addBookingRequest(Reservation reservation) {

        bookingQueue.offer(reservation);

        System.out.println("Request added for " + reservation.getGuestName());
    }

    public void processBookings() {

        System.out.println("\nProcessing Booking Queue...\n");

        while(!bookingQueue.isEmpty()){

            Reservation reservation = bookingQueue.poll();

            bookingService.allocateRoom(reservation);
        }
    }
}