package com.bookmystay.booking;

import com.bookmystay.inventory.RoomType;

public class Reservation {

    private String guestName;
    private RoomType roomType;
    private String roomId;
    private String reservationId;

    public Reservation(String guestName, RoomType roomType) {

        this.guestName = guestName;
        this.roomType = roomType;

        this.reservationId = "R" + System.currentTimeMillis();
    }

    public String getGuestName() {
        return guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }
    
    public String getReservationId() {
        return reservationId;
    }
}