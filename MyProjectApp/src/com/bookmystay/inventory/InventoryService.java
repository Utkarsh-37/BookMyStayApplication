package com.bookmystay.inventory;

import java.util.EnumMap;
import java.util.Map;

public class InventoryService {

    private Map<RoomType, Integer> roomCounts;
    private Map<RoomType, Double> roomPrices;

    public InventoryService() {

        roomCounts = new EnumMap<>(RoomType.class);
        roomPrices = new EnumMap<>(RoomType.class);
    }

    public void setRoomDetails(RoomType type, int count, double price) {

        roomCounts.put(type, count);
        roomPrices.put(type, price);
    }

    public int getAvailableRooms(RoomType type) {
        return roomCounts.get(type);
    }

    public double getRoomPrice(RoomType type) {
        return roomPrices.get(type);
    }

    public void updateRoomCount(RoomType type, int count) {
        roomCounts.put(type, count);
    }

    public void updateRoomPrice(RoomType type, double price) {
        roomPrices.put(type, price);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory\n");

        for (RoomType type : RoomType.values()) {

            int count = roomCounts.get(type);
            double price = roomPrices.get(type);

            System.out.println(type +
                    " | Available Rooms: " + count +
                    " | Price per Night: " + price);
        }
    }
}