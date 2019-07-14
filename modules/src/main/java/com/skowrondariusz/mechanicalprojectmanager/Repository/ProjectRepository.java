package com.skowrondariusz.mechanicalprojectmanager.Repository;

import com.skowrondariusz.mechanicalprojectmanager.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>{

//    List<Project> findAllById(Long id);
}

