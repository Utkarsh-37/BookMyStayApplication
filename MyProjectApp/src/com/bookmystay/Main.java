/*
 * UC6: Booking History & Reporting
 * --------------------------------
 * - Stores reservations in a List<Reservation>.
 * - Allows administrators to view booking history.
 * - Supports reservation cancellation.
 * - Generates simple booking reports for audit and support.
 *
 * @version 6.0
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

		BookingHistoryService historyService = new BookingHistoryService();

		BookingService bookingService = new BookingService(inventory, historyService);

		BookingQueueService bookingQueueService =
				new BookingQueueService(bookingService);

		System.out.print("Enter number of booking requests:");

		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {

			System.out.print("Enter guest name:");
			String name = sc.next();

			System.out.print("Enter room type (SINGLE/DOUBLE/SUITE)");
			RoomType type;

			try {
				type = RoomType.valueOf(sc.next().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid room type. Try again.");
				i--;
				continue;
			}

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
		while(true){

			System.out.println("""
					--- Admin Reporting ---
					1 View Booking History
					2 Cancel Reservation
					3 Generate Report
					4 Exit
					""");

			int option = sc.nextInt();

			switch(option){

			case 1 -> historyService.showAllReservations();

			case 2 -> {

				System.out.print("Enter Reservation ID to cancel: ");

				String id = sc.next();

				historyService.cancelReservation(id);
			}

			case 3 -> historyService.generateReport();

			case 4 -> {
				System.out.println("Exiting reporting module.");
				return;
			}
			}
		}

	}
}