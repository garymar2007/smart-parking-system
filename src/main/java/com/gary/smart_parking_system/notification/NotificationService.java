package com.gary.smart_parking_system.notification;

import com.gary.smart_parking_system.event.VehicleEnteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event) {
        System.out.println("\uD83D\uDCE9 Notification sent for Vehicle " + event.vehicleNumber()
                + " entered at" + event.entryTime() + ". Welcome!");
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleEnteredEvent event) {
        System.out.println("\uD83D\uDCE9 Notification sent for Vehicle " + event.vehicleNumber()
                + " exited at" + event.entryTime() + ". Thank you for visiting!");
    }
}
