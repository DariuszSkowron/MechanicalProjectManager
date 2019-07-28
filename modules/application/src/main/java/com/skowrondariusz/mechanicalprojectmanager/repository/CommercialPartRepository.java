package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialPartRepository extends JpaRepository<CommercialPart, Long> {
}
