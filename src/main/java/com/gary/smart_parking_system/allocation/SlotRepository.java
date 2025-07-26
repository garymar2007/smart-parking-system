package com.gary.smart_parking_system.allocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
