package com.bookmystay.booking;

import com.bookmystay.inventory.RoomType;

public class Reservation {

    private String guestName;
    private RoomType roomType;
    private String roomId;

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

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }
}