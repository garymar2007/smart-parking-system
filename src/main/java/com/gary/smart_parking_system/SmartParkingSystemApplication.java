package com.gary.smart_parking_system;

import com.gary.smart_parking_system.allocation.SlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initSlots(SlotRepository slotRepository) {
		return args -> {
			if (slotRepository.count() == 0) {
				for (int i = 1; i <= 10; i++) {
					slotRepository.save(new com.gary.smart_parking_system.allocation.Slot(
							null, "Slot-A" + i, true, null));
				}
				for (int i = 1; i <= 10; i++) {
					slotRepository.save(new com.gary.smart_parking_system.allocation.Slot(
							null, "Slot-B" + i, true, null));
				}
			}
		};
	}
}
