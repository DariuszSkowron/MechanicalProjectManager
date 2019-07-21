package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ProcessedPartViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.ProcessedPart;
import com.skowrondariusz.mechanicalprojectmanager.repository.MechanicalProcessingRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ProcessedPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessedPartController {

    private ProcessedPartRepository processedPartRepository;
    private MechanicalProcessingRepository mechanicalProcessingRepository;
    private Mapper mapper;

    public ProcessedPartController(ProcessedPartRepository processedPartRepository, MechanicalProcessingRepository mechanicalProcessingRepository, Mapper mapper) {
        this.processedPartRepository = processedPartRepository;
        this.mechanicalProcessingRepository = mechanicalProcessingRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<ProcessedPartViewModel> all() {
        var parts = this.processedPartRepository.findAll();


        var processedPartVievModel = parts.stream()
                .map(part -> this.mapper.convertToProcessedPartViewModel(part))
                .collect(Collectors.toList());

        return processedPartVievModel;
    }

    @GetMapping("/byId/{id}")
    public ProcessedPartViewModel byId(@PathVariable String id) {
        var part = this.processedPartRepository.findById(Long.valueOf(id)).orElse(null);

        if (part == null) {
            throw new EntityNotFoundException();
        }

        var processedPartViewModel = this.mapper.convertToProcessedPartViewModel(part);

        return processedPartViewModel;
    }


    @GetMapping("/byMechanicalProcessing/{mechanicalProcessingId}")
    public List<ProcessedPartViewModel> byMechanicalProcessing(@PathVariable String mechanicalProcessingId) {
        List<ProcessedPart> parts = new ArrayList<>();

        var mechanicalProcessing = this.mechanicalProcessingRepository.findById(Long.valueOf(mechanicalProcessingId));
        if (mechanicalProcessing.isPresent()) {
            parts = this.processedPartRepository.findAllByMechanicalProcessing(mechanicalProcessing.get());
        }

        var partsViewModel = parts.stream()
                .map(part -> this.mapper.convertToProcessedPartViewModel(part))
                .collect(Collectors.toList());

        return partsViewModel;
    }

    @PostMapping
    public ProcessedPart save(@RequestBody ProcessedPartViewModel processedPartCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var partEntity = this.mapper.convertToProcessedPartEntity(processedPartCreateViewModel);

        this.processedPartRepository.save(partEntity);

        return partEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.processedPartRepository.deleteById(Long.valueOf(id));
    }
}
