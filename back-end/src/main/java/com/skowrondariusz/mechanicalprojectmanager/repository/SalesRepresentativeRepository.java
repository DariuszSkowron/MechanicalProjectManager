package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.SalesRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepresentativeRepository extends JpaRepository<SalesRepresentative, Long> {
    List<SalesRepresentative> findByManufacturer(Manufacturer manufacturer);
}
