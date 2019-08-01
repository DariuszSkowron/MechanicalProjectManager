package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsOrdersRepository extends JpaRepository<PartsOrders, Long> {
}
