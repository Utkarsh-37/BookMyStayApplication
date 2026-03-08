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
        return roomCounts.getOrDefault(type, 0);
    }

    public double getRoomPrice(RoomType type) {
        return roomPrices.getOrDefault(type, 0.0);
    }

    public Map<RoomType, Integer> getRoomCounts() {
        return roomCounts;
    }

    public Map<RoomType, Double> getRoomPrices() {
        return roomPrices;
    }
    
    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory\n");

        for (RoomType type : RoomType.values()) {

            int count = roomCounts.getOrDefault(type, 0);
            double price = roomPrices.getOrDefault(type, 0.0);

            System.out.println(type +
                    " | Available Rooms: " + count +
                    " | Price per Night: " + price);
        }
    }
}