package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.MechanicalProcessingViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;
import com.skowrondariusz.mechanicalprojectmanager.repository.MechanicalProcessingRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/mechanicalProcessing")
@CrossOrigin(origins = "http://localhost:4200")
public class MechanicalProcessingController {

    private MechanicalProcessingRepository mechanicalProcessingRepository;
    private Mapper mapper;

    public MechanicalProcessingController(MechanicalProcessingRepository mechanicalProcessingRepository, Mapper mapper) {
        this.mechanicalProcessingRepository = mechanicalProcessingRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<MechanicalProcessing> all() {
        var allMechanicalProcessing = this.mechanicalProcessingRepository.findAll();
        return allMechanicalProcessing;
    }

    @PostMapping
    public MechanicalProcessing save(@RequestBody MechanicalProcessingViewModel mechanicalProcessingViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var mechanicalProcessingEntity = this.mapper.convertToMechanicalProcessingEntity(mechanicalProcessingViewModel);

        this.mechanicalProcessingRepository.save(mechanicalProcessingEntity);

        return mechanicalProcessingEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.mechanicalProcessingRepository.deleteById(Long.valueOf(id));
    }
}
