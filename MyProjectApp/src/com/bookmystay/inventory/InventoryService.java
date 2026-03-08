package com.bookmystay.inventory;

import java.util.EnumMap;
import java.util.Map;

public class InventoryService {

    private Map<RoomType, Integer> roomCounts = new EnumMap<>(RoomType.class);
    private Map<RoomType, Double> roomPrices = new EnumMap<>(RoomType.class);

    public void setRoomDetails(RoomType type, int count, double price) {
        roomCounts.put(type, count);
        roomPrices.put(type, price);
    }

    public void updateRoomCount(RoomType type, int count) {
        roomCounts.put(type, count);
    }

    public void updateRoomPrice(RoomType type, double price) {
        roomPrices.put(type, price);
    }

    public int getAvailableRooms(RoomType type) {
        return roomCounts.getOrDefault(type, 0);
    }

    public double getRoomPrice(RoomType type) {
        return roomPrices.getOrDefault(type, 0.0);
    }

    public void reduceRoom(RoomType type) {
        int current = roomCounts.get(type);
        roomCounts.put(type, current - 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory\n");

        for (RoomType type : RoomType.values()) {

            System.out.println(type +
                    " | Available: " + roomCounts.get(type) +
                    " | Price: " + roomPrices.get(type));
        }
    }
}