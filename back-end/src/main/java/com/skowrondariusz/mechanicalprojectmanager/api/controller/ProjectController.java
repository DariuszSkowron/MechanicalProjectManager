package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProjectController {

   
    private ProjectRepository projectRepository;
    
    public ProjectController(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    
    

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        System.out.println("Get all projects...");
        var projects = projectRepository.findAll();
        return new ArrayList<>(projects);
    }

    @PostMapping(value = "/projects/create")
    public Project postProject(@RequestBody Project project) {
    
        return projectRepository.save(new Project(project.getName(), project.getProjectNumber()));
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") long id) {
        System.out.println("Delete project with ID = " + id + "...");

        projectRepository.deleteById(id);

        return new ResponseEntity<>("Project has been deleted", HttpStatus.OK);
    }

    @DeleteMapping("/projects/delete")
    public ResponseEntity<String> deleteAllProjects() {
        System.out.println("Delete all projects...");

        projectRepository.deleteAll();

        return new ResponseEntity<>("All projects have been deleted! You are in troubles!!", HttpStatus.OK);
    }
    
    @GetMapping(value = "projects/number/{projectNumber}")
    public List<Project> findByProjectNumber(@PathVariable int projectNumber) {

        var project = this.projectRepository.findByProjectNumber(projectNumber);

        if (project == null){
            throw new EntityNotFoundException();
        }
        return project;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        System.out.println("Update project with ID= " + id + "...");

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