package com.bookmystay.booking;

import com.bookmystay.inventory.InventoryService;
import com.bookmystay.inventory.RoomType;

import java.util.*;

public class BookingService {

    private Set<String> bookedRoomIds = new HashSet<>();

    private Map<RoomType, Set<String>> assignedRooms = new HashMap<>();

    private InventoryService inventoryService;

    public BookingService(InventoryService inventoryService) {

        this.inventoryService = inventoryService;

        for (RoomType type : RoomType.values()) {
            assignedRooms.put(type, new HashSet<>());
        }
    }

    public Reservation allocateRoom(Reservation reservation) {

        RoomType type = reservation.getRoomType();

        if (inventoryService.getAvailableRooms(type) <= 0) {

            System.out.println("Booking failed for " +
                    reservation.getGuestName() +
                    " (No rooms available)");

            return null;
        }

        String roomId = generateRoomId(type);

        reservation.setRoomId(roomId);

        bookedRoomIds.add(roomId);

        assignedRooms.get(type).add(roomId);

        inventoryService.reduceRoom(type);

        System.out.println("Booking confirmed for "
                + reservation.getGuestName()
                + " | Reservation ID: " + reservation.getReservationId()
                + " | Room ID: " + roomId);

        return reservation;
    }

    private String generateRoomId(RoomType type) {

        String prefix = type.toString().substring(0,1);

        String roomId;

        do {

            roomId = prefix + (int)(Math.random()*1000);

        } while(bookedRoomIds.contains(roomId));

        return roomId;
    }

    public void displayAssignedRooms() {

        System.out.println("\nAssigned Rooms\n");

        for(RoomType type : assignedRooms.keySet()){

            System.out.println(type + " → " + assignedRooms.get(type));
        }
    }
}