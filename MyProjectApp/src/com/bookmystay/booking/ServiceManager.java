package com.bookmystay.booking;

import java.util.*;

public class ServiceManager {

    private Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addService(String reservationId, Service service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getServiceName()
                + " added to reservation " + reservationId);
    }

    public void showServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {

            System.out.println("No services added.");
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId);

        for(Service s : services){
            System.out.println(s);
        }
    }

    public double calculateServiceCost(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for(Service s : services){
            total += s.getPrice();
        }

        return total;
    }
}