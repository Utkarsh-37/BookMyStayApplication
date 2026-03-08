package com.bookmystay.admin;

import com.bookmystay.inventory.InventoryService;
import com.bookmystay.inventory.RoomType;

import java.util.Scanner;

public class HotelAdmin {

    private InventoryService inventoryService;

    public HotelAdmin(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void setupInventory(Scanner sc) {

        System.out.println("Setup Room Inventory\n");

        for (RoomType type : RoomType.values()) {

            System.out.print("Enter number of " + type + " rooms:");
            int count = sc.nextInt();

            System.out.print("Enter price per night for " + type + ":");
            double price = sc.nextDouble();

            inventoryService.setRoomDetails(type, count, price);
        }

        System.out.println("\nInventory setup completed.\n");
    }

    public void viewInventory() {
        inventoryService.displayInventory();
    }
}