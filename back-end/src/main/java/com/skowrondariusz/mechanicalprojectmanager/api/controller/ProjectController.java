package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ProjectViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProjectRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectRepository projectRepository;
    private Mapper mapper;

    
    public ProjectController(ProjectRepository projectRepository, Mapper mapper){
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }


    @GetMapping("/all")
    public List<ProjectViewModel> getAllProjects() {
        var projects = projectRepository.findAll();

        return projects.stream()
                .map(project -> this.mapper.convertToProjectViewModel(project))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "create")
    public Project postProject(@RequestBody ProjectViewModel projectViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var projectEntity = this.mapper.convertToProjectEntity(projectViewModel);

        this.projectRepository.save(projectEntity);

        return projectEntity;

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
    public List<ProjectViewModel> findByProjectNumber(@PathVariable int projectNumber) {

        var project = this.projectRepository.findByProjectNumber(projectNumber);
        if (project == null){
            throw new EntityNotFoundException();
        }

        return project.stream()
                .map(project1 -> mapper.convertToProjectViewModel(project1))
                .collect(Collectors.toList());
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
