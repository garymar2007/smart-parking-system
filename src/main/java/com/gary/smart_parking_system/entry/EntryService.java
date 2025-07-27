package com.gary.smart_parking_system.entry;

import com.gary.smart_parking_system.event.VehicleEnteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {
    private final ParkingEntryRepository parkingEntryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public EntryService(
            final ParkingEntryRepository parkingEntryRepository,
            final ApplicationEventPublisher applicationEventPublisher
    ) {
        this.parkingEntryRepository = parkingEntryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //save vehicle entry details to DB
    // allocate a parking slot
    //send notification to the user

    public void vehicleEntry(final String vehicleNumber) {
       // create a new ParkingEntry object
        ParkingEntry entry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);

        // save the entry to the database
        parkingEntryRepository.save(entry);

        applicationEventPublisher.publishEvent(new VehicleEnteredEvent(
            vehicleNumber, entry.getEntryTime()
        ));


    }

}
