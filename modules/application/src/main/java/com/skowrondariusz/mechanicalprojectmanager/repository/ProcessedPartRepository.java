package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.model.ProcessedPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedPartRepository extends JpaRepository<ProcessedPart, Long> {
    List<ProcessedPart> findAllByMechanicalProcessing(MechanicalProcessing mechanicalProcessing);
}
