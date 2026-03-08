package com.bookmystay.booking;

import java.util.*;

public class BookingHistoryService {

    private List<Reservation> bookingHistory = new ArrayList<>();

    public void addReservation(Reservation reservation) {

        bookingHistory.add(reservation);
    }

    public void showAllReservations() {

        if (bookingHistory.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\nBooking History\n");

        for (Reservation r : bookingHistory) {

            System.out.println(
                    "Reservation ID: " + r.getReservationId() +
                    " | Guest: " + r.getGuestName() +
                    " | Room Type: " + r.getRoomType() +
                    " | Room ID: " + r.getRoomId()
            );
        }
    }

    public void cancelReservation(String reservationId) {

        Iterator<Reservation> iterator = bookingHistory.iterator();

        while(iterator.hasNext()){

            Reservation r = iterator.next();

            if(r.getReservationId().equals(reservationId)){

                iterator.remove();

                System.out.println("Reservation cancelled: " + reservationId);

                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    public int getTotalBookings() {
        return bookingHistory.size();
    }

    public void generateReport(){

        System.out.println("\nBooking Report");

        System.out.println("Total Reservations: " + bookingHistory.size());
    }
}