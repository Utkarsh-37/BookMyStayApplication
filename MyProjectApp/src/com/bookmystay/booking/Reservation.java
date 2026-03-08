package com.bookmystay.booking;

import com.bookmystay.inventory.RoomType;

public class Reservation {

    private String guestName;
    private RoomType roomType;

    public Reservation(String guestName, RoomType roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}