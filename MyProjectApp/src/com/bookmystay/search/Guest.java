package com.bookmystay.search;

import com.bookmystay.inventory.RoomType;

import java.util.Scanner;

public class Guest {

    private SearchService searchService;

    public Guest(SearchService searchService) {
        this.searchService = searchService;
    }

    public void searchRooms() {
        searchService.displayAvailableRooms();
    }

    public void checkRoomAvailability(Scanner sc) {

        System.out.println("\nEnter room type to check availability (SINGLE / DOUBLE / SUITE):");

        String input = sc.next().toUpperCase();

        try {

            RoomType type = RoomType.valueOf(input);

            boolean available = searchService.checkAvailability(type);

            if (available) {
                System.out.println(type + " room is available.");
            }

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid room type.");
        }
    }
}