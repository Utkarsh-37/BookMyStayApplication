/*
 * UC3: Booking Request Queue (First-Come-First-Served)
 * ----------------------------------------------------
 * Introduced a FIFO booking queue to accept and order booking requests fairly.
 * Option 6 now accepts N users in one go; each request is enqueued in sequence
 * with a fixed 3000 ms delay between users to mimic staggered arrivals.
 * Synchronized queue operations keep things simple (no concurrent collections).
 *
 * @version 3.0
 * @author developer
*/
package com.bookmystay;

import com.bookmystay.admin.HotelAdmin;
import com.bookmystay.booking.*;
import com.bookmystay.inventory.*;
import com.bookmystay.search.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        InventoryService inventory = new InventoryService();
        HotelAdmin admin = new HotelAdmin(inventory);

        admin.setupInventory(sc);
        admin.viewInventory();

        System.out.print("\nDo you want to edit inventory? (y/n)");
        String editChoice = sc.next();

        if(editChoice.equalsIgnoreCase("y")){
            admin.editInventory(sc);
        }

        admin.viewInventory();

        SearchService searchService = new SearchService(inventory);
        Guest guest = new Guest(searchService);

        guest.searchMenu(sc);

        BookingQueueService bookingService =
                new BookingQueueService(inventory);

        System.out.print("Enter number of booking requests:");

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter guest name:");
            String name = sc.next();

            System.out.print("Enter room type (SINGLE/DOUBLE/SUITE)");
            RoomType type = RoomType.valueOf(sc.next().toUpperCase());

            bookingService.addBookingRequest(
                    new Reservation(name, type));

            Thread.sleep(3000); // delay between requests
        }

        System.out.println("\nProcessing bookings...\n");

        bookingService.processBookings();

        admin.viewInventory();
    }
}