package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.SalesRepresentativeViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.SalesRepresentative;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.SalesRepresentativeRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salesRepresentative")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesRepresentativeController {


    private SalesRepresentativeRepository salesRepresentativeRepository;
    private ManufacturerRepository manufacturerRepository;
    private Mapper mapper;

    public SalesRepresentativeController(SalesRepresentativeRepository salesRepresentativeRepository, ManufacturerRepository manufacturerRepository, Mapper mapper) {
        this.salesRepresentativeRepository = salesRepresentativeRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<SalesRepresentativeViewModel> all() {
        var salesRepresentative = this.salesRepresentativeRepository.findAll();

        return salesRepresentative.stream()
                .map(rep -> this.mapper.convertToSalesRepresentativeViewModel(rep))
                .collect(Collectors.toList());
    }

    @GetMapping("byId/{id}")
    public SalesRepresentativeViewModel byId(@PathVariable Long id) {
        SalesRepresentative salesRepresentative = this.salesRepresentativeRepository.findById(id).orElse(null);

        if (salesRepresentative == null) {
            throw new EntityNotFoundException();
        }

        SalesRepresentativeViewModel salesRepresentativeViewModel = this.mapper.convertToSalesRepresentativeViewModel(salesRepresentative);

        return salesRepresentativeViewModel;
    }

    @GetMapping("/byManufacturer/{manufacturerId}")
    public List<SalesRepresentativeViewModel> salesRepresentative(@PathVariable String manufacturerId) {

        var manufacturer = this.manufacturerRepository.findById(Long.valueOf(manufacturerId));

        List<SalesRepresentative> salesRepresentative = new ArrayList<>();

        if (manufacturer.isPresent()) {
            salesRepresentative = this.salesRepresentativeRepository.findByManufacturer(manufacturer.get());
        }

        return salesRepresentative.stream()
                .map(salRep -> this.mapper.convertToSalesRepresentativeViewModel(salRep))
                .collect(Collectors.toList());
    }

    @PostMapping
    public SalesRepresentative save(@RequestBody SalesRepresentativeViewModel salesRepresentativeViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var salesRepresentativeEntity = this.mapper.convertToSalesRepresentativeEntity(salesRepresentativeViewModel);

        this.salesRepresentativeRepository.save(salesRepresentativeEntity);

        return salesRepresentativeEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.salesRepresentativeRepository.deleteById(id);
    }
}
