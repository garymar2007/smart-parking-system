package com.gary.smart_parking_system.allocation;

import com.gary.smart_parking_system.event.VehicleEnteredEvent;
import com.gary.smart_parking_system.event.VehicleExitEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SlotAllocationService {
    private final SlotRepository slotRepository;
    public SlotAllocationService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent event) {
        // find the available slot to allocate
        Slot slot = slotRepository.findFirstByAvailableTrue().orElseThrow(() -> new RuntimeException("\uD83D\uDEAB No available slots found"));
        slot.setAvailable(false);
        slot.setVehicleNumber(event.vehicleNumber());
        slotRepository.save(slot);

        // send notification to the user (not implemented here)
        System.out.println("\uD83C\uDD7F\uFE0F Allocated slot: " + slot.getSlotCode() + " for vehicle: " + event.vehicleNumber());
    }

    @EventListener
    public void handleVehicleExit(VehicleExitEvent event) {
        // find the available slot to allocate
        Slot slot = slotRepository.findFirstByVehicleNumber(event.vehicleNumber())
                .orElseThrow(() -> new RuntimeException("\uD83D\uDEAB No slot found for a given vehicle: " + event.vehicleNumber()));
        slot.setAvailable(true);
        slot.setVehicleNumber(event.vehicleNumber());
        slotRepository.save(slot);

        // send notification to the user (not implemented here)
        System.out.println("\uD83C\uDD7F\uFE0F Free slot: " + slot.getSlotCode() + " for vehicle: " + event.vehicleNumber());
    }
}
