package com.bookmystay.search;

import com.bookmystay.inventory.InventoryService;
import com.bookmystay.inventory.RoomType;

public class SearchService {

    private InventoryService inventoryService;

    public SearchService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void displayAvailableRooms() {

        System.out.println("\nAvailable Rooms\n");

        for (RoomType type : RoomType.values()) {

            int available = inventoryService.getAvailableRooms(type);

            if (available > 0) {

                double price = inventoryService.getRoomPrice(type);

                System.out.println(type +
                        " | Available: " + available +
                        " | Price: " + price +
                        " | Amenities: Basic amenities included");
            }
        }
    }

    public boolean checkAvailability(RoomType type) {

        int available = inventoryService.getAvailableRooms(type);

        if (available <= 0) {
            System.out.println("Sorry, " + type + " rooms are not available.");
            return false;
        }

        return true;
    }
}