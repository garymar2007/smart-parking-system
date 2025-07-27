package com.gary.smart_parking_system.billing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingRecord, Long> {
    // This repository interface extends JpaRepository to provide CRUD operations for BillingRecord entities.
    // It allows for easy interaction with the database to manage billing records.
}
