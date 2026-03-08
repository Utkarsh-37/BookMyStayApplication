package com.bookmystay.booking;

import com.bookmystay.inventory.InventoryService;
import com.bookmystay.inventory.RoomType;

import java.util.LinkedList;
import java.util.Queue;

public class BookingQueueService {

    private Queue<Reservation> bookingQueue = new LinkedList<>();
    private InventoryService inventoryService;

    public BookingQueueService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addBookingRequest(Reservation reservation) {

        bookingQueue.offer(reservation);

        System.out.println("Booking request added for "
                + reservation.getGuestName());
    }

    public void processBookings() {

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();

            RoomType type = r.getRoomType();

            if (inventoryService.getAvailableRooms(type) > 0) {

                inventoryService.reduceRoom(type);

                System.out.println("Booking confirmed for "
                        + r.getGuestName() +
                        " (" + type + ")");

            } else {

                System.out.println("Booking failed for "
                        + r.getGuestName() +
                        " — No rooms available");
            }
        }
    }
}