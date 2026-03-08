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

        for (RoomType type : RoomType.values()) {

            System.out.print("Enter number of " + type + " rooms:");
            int count = sc.nextInt();

            System.out.print("Enter price for " + type + ":");
            double price = sc.nextDouble();

            inventoryService.setRoomDetails(type, count, price);
        }
    }

    public void editInventory(Scanner sc) {

        System.out.println("""
                1 Edit Room Count
                2 Edit Room Price
                """);

        int choice = sc.nextInt();

        System.out.println("Enter room type (SINGLE/DOUBLE/SUITE)");

        RoomType type = RoomType.valueOf(sc.next().toUpperCase());

        if (choice == 1) {

            System.out.println("Enter new count:");
            int count = sc.nextInt();

            inventoryService.updateRoomCount(type, count);

        } else if (choice == 2) {

            System.out.println("Enter new price:");
            double price = sc.nextDouble();

            inventoryService.updateRoomPrice(type, price);
        }
    }

    public void viewInventory() {
        inventoryService.displayInventory();
    }
}