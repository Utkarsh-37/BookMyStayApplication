/*
 * UC4: Reservation Confirmation & Room Allocation
 * -----------------------------------------------
 * Added FIFO processing of queued booking requests with atomic room allocation:
 * - Dequeues next request, decrements inventory if available, and assigns a unique room ID.
 * - Prevents double-booking via a Set of booked room IDs and per-type assignment tracking.
 * - Options 8 & 9 confirm one/all reservations; option 10 displays confirmed allocations.
 *
 * @version 4.0
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

        BookingService bookingService = new BookingService(inventory);

        BookingQueueService bookingQueueService =
                new BookingQueueService(bookingService);

        System.out.print("Enter number of booking requests:");

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter guest name:");
            String name = sc.next();

            System.out.print("Enter room type (SINGLE/DOUBLE/SUITE)");
            RoomType type = RoomType.valueOf(sc.next().toUpperCase());

            bookingQueueService.addBookingRequest(new Reservation(name,type));

            Thread.sleep(3000); // delay between requests
        }

        System.out.println("\nProcessing bookings...\n");

        bookingQueueService.processBookings();

        bookingService.displayAssignedRooms();

        admin.viewInventory();

    }
}