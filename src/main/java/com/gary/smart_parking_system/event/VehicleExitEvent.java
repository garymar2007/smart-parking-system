package com.gary.smart_parking_system.event;

import java.time.LocalDateTime;

public record VehicleExitEvent(String vehicleNumber, LocalDateTime entryTime, LocalDateTime exitTime) {
    // This record class is used to encapsulate the event of a vehicle exiting the parking system.
    // It contains the vehicle number and the slot code where the vehicle was parked.
}
