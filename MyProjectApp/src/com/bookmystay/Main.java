/*
 * UC1: Room Inventory Setup & Management
 * --------------------------------------
 * Initialize room types (Single, Double, Suite)
 * Store room counts and prices
 * Support dynamic inventory updates
 *
 * @version 1.0
 * @author developer
*/
package com.bookmystay;

import com.bookmystay.admin.HotelAdmin;
import com.bookmystay.inventory.InventoryService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InventoryService inventoryService = new InventoryService();
        HotelAdmin admin = new HotelAdmin(inventoryService);

        admin.setupInventory(sc);

        admin.viewInventory();
    }
}