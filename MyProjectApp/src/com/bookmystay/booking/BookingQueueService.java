package com.bookmystay.booking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public List<Reservation> processBookings() {

        List<Reservation> confirmedReservations = new ArrayList<>();

        while(!bookingQueue.isEmpty()){

            Reservation reservation = bookingQueue.poll();

            Reservation confirmed = bookingService.allocateRoom(reservation);

            if(confirmed != null){
                confirmedReservations.add(confirmed);
            }
        }

        return confirmedReservations;
    }
}