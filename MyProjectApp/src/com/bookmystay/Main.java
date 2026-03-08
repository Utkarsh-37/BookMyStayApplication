/*
 * UC5: Add-On Service Selection
 * -----------------------------
 * Introduces optional services that can be attached to confirmed reservations:
 * - Allows guests to select additional services such as Breakfast, Airport Pickup, and Spa.
 * - Uses a Map<ReservationID, List<Service>> to maintain a one-to-many relationship
 *   between a reservation and its selected services.
 *
 * @version 5.0
 * @author developer
 */
package com.bookmystay;

import com.bookmystay.admin.HotelAdmin;
import com.bookmystay.booking.*;
import com.bookmystay.inventory.*;
import com.bookmystay.search.*;

import java.util.*;

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

        List<Reservation> reservations = bookingQueueService.processBookings();

        bookingService.displayAssignedRooms();

        admin.viewInventory();

        ServiceManager serviceManager = new ServiceManager();
        
        for(Reservation r : reservations){

            System.out.println("\nAdd services for Reservation: " + r.getReservationId());

            while(true){

                System.out.println("""
                1 Breakfast (₹500)
                2 Airport Pickup (₹1000)
                3 Spa (₹1500)
                4 Done
                """);

                int choice = sc.nextInt();

                switch(choice){

                    case 1 -> serviceManager.addService(
                            r.getReservationId(),
                            new Service("Breakfast",500));

                    case 2 -> serviceManager.addService(
                            r.getReservationId(),
                            new Service("Airport Pickup",1000));

                    case 3 -> serviceManager.addService(
                            r.getReservationId(),
                            new Service("Spa",1500));

                    case 4 -> {
                        double cost =
                                serviceManager.calculateServiceCost(
                                        r.getReservationId());

                        System.out.println("Total Service Cost: ₹" + cost);

                        serviceManager.showServices(r.getReservationId());

                        break;
                    }
                }

                if(choice == 4) break;
            }
        }

    }
}