package com.bookmystay.search;

import com.bookmystay.inventory.InventoryService;
import com.bookmystay.inventory.RoomType;

public class SearchService {

    private InventoryService inventoryService;

    public SearchService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void checkAvailability(RoomType type) {

        int available = inventoryService.getAvailableRooms(type);

        if (available > 0) {

            System.out.println(type +
                    " Available: " + available +
                    " Price: " + inventoryService.getRoomPrice(type));

        } else {

            System.out.println("No rooms available.");
        }
    }
}