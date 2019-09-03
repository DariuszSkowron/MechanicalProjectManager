package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.repository.PartsOrderRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectRepository projectRepository;
    private PartsOrderRepository partsOrderRepository;
    
    public ProjectController(ProjectRepository projectRepository, PartsOrderRepository partsOrderRepository){
        this.projectRepository = projectRepository;
        this.partsOrderRepository = partsOrderRepository;
    }


    @GetMapping("/all")
    public List<Project> getAllProjects() {
        var projects = projectRepository.findAll();
        return new ArrayList<>(projects);
    }

    @PostMapping(value = "create")
    public Project postProject(@RequestBody Project project) {
    
        return projectRepository.save(project);
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") long id) {

        projectRepository.deleteById(id);

        return new ResponseEntity<>("Project has been deleted", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllProjects() {

        projectRepository.deleteAll();

        return new ResponseEntity<>("All projects have been deleted! You are in troubles!!", HttpStatus.OK);
    }
    
    @GetMapping(value = "/number/{projectNumber}")
    public List<Project> findByProjectNumber(@PathVariable int projectNumber) {

        var project = this.projectRepository.findByProjectNumber(projectNumber);

        if (project == null){
            throw new EntityNotFoundException();
        }
        return project;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {


        Optional<Project> projectData = projectRepository.findById(id);

        if (projectData.isPresent()) {
            Project projectInserted = projectData.get();
            projectInserted.setName(project.getName());
            return new ResponseEntity<>(projectRepository.save(projectInserted), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
