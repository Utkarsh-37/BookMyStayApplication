
/*
 * UC2: Room Search & Availability Check
 * --------------------------------------
 * Added a read‑only search flow allowing guests to view available room types,
 * check pricing, and see amenities without modifying inventory. 
 * Uses snapshot-based lookups to ensure accurate, up‑to‑date availability
 * while preventing accidental mutation of UC1’s core inventory state.
 * 
 * @version 2.0
 * @author developer
*/
package com.bookmystay;

import com.bookmystay.admin.HotelAdmin;
import com.bookmystay.inventory.*;
import com.bookmystay.search.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InventoryService inventoryService = new InventoryService();
        HotelAdmin admin = new HotelAdmin(inventoryService);

        admin.setupInventory(sc);

        SearchService searchService = new SearchService(inventoryService);
        Guest guest = new Guest(searchService);

        guest.searchRooms();

        guest.checkRoomAvailability(sc);
    }
}