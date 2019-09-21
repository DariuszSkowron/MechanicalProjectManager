package com.skowrondariusz.mechanicalprojectmanager.repository;

import com.skowrondariusz.mechanicalprojectmanager.model.PartsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsOrderRepository extends JpaRepository<PartsOrder, Long> {

    PartsOrder findByProjectId(long id);
}
