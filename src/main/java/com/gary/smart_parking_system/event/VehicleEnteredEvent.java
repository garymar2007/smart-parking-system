package com.gary.smart_parking_system.event;

import java.time.LocalDateTime;

public record VehicleEnteredEvent(String vehicleNumber, LocalDateTime entryTime) {
    // This record class is used to encapsulate the event of a vehicle entering the parking system.
    // It contains the vehicle number and the slot code where the vehicle is parked.
}
