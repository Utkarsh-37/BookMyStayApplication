package com.bookmystay.search;

import com.bookmystay.inventory.RoomType;

import java.util.Scanner;

public class Guest {

    private SearchService searchService;

    public Guest(SearchService searchService) {
        this.searchService = searchService;
    }

    public void searchMenu(Scanner sc) {

        while (true) {

            System.out.println("""
                    1 Check Single Room
                    2 Check Double Room
                    3 Check Suite Room
                    4 Exit
                    """);

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> searchService.checkAvailability(RoomType.SINGLE);
                case 2 -> searchService.checkAvailability(RoomType.DOUBLE);
                case 3 -> searchService.checkAvailability(RoomType.SUITE);
                case 4 -> {
                    return;
                }
            }
        }
    }
}