package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.model.SalesRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepresentativeRepository extends JpaRepository<SalesRepresentative, Long> {
    SalesRepresentative findByManufacturer(Manufacturer manufacturer);
}
