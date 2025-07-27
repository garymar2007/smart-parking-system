package com.gary.smart_parking_system.entry;

import com.gary.smart_parking_system.event.VehicleExitEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {
    private final ParkingEntryRepository parkingEntryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ExitService(
            final ParkingEntryRepository parkingEntryRepository,
            final ApplicationEventPublisher applicationEventPublisher
    ) {
        this.parkingEntryRepository = parkingEntryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void vehicleExit(final String vehicleNumber) {
        // find the entry by vehicle number
        ParkingEntry entry = parkingEntryRepository.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(() -> new IllegalArgumentException("\uD83D\uDEAB No active entry found for Vehicle: " + vehicleNumber));

        // update the exit time and availability
        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);

        // save the updated entry to the database
        parkingEntryRepository.save(entry);

        // publish an event for vehicle exit
        applicationEventPublisher.publishEvent(new VehicleExitEvent(
                vehicleNumber, entry.getEntryTime(), entry.getExitTime()
        ));
    }
}
