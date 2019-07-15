package com.skowrondariusz.mechanicalprojectmanager.Controller;


import com.skowrondariusz.mechanicalprojectmanager.Model.Project;
import com.skowrondariusz.mechanicalprojectmanager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        System.out.println("Get all projects...");

        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);

        return projects;
    }

    @PostMapping(value = "/projects/create")
    public Project postProject(@RequestBody Project project) {

        Project projectAdded = projectRepository.save(new Project(project.getName()));
        return projectAdded;
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

    @GetMapping(value = "projects/id/{id}")
    public Optional<Project> findById(@PathVariable long id) {

        Optional<Project> projects = projectRepository.findById(id);
        return projects;
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
