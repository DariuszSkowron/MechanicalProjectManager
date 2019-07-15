package com.skowrondariusz.mechanicalprojectmanager.Repository;

import com.skowrondariusz.mechanicalprojectmanager.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

//    List<Project> findAllById(Long id);
}

