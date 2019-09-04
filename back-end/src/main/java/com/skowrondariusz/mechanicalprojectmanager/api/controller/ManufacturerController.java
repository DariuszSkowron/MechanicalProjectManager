package com.skowrondariusz.mechanicalprojectmanager.api.controller;

import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ManufacturerViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.model.Project;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.ManufacturerRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturer")
@CrossOrigin(origins = "http://localhost:4200")
public class ManufacturerController {

    private ManufacturerRepository manufacturerRepository;
    private Mapper mapper;
    private CommercialPartRepository commercialPartRepository;

    public ManufacturerController(ManufacturerRepository manufacturerRepository, CommercialPartRepository commercialPartRepository, Mapper mapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.commercialPartRepository = commercialPartRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Manufacturer> all() {
        return this.manufacturerRepository.findAll();
    }

    @PostMapping
    public Manufacturer save(@RequestBody ManufacturerViewModel manufacturerViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var manufacturerEntity = this.mapper.convertToManufacturerEntity(manufacturerViewModel);

        this.manufacturerRepository.save(manufacturerEntity);

        return manufacturerEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        var manufacturer = manufacturerRepository.getManufacturerById(id);

        if(manufacturer.getCommercialParts() != null){
            var commercialPartsList = this.commercialPartRepository.findAllByManufacturer(manufacturer);
            commercialPartsList.forEach(commercialPart -> commercialPart.setManufacturer(manufacturerRepository.getManufacturerById(1)));
            System.out.println("test2");
        }

        this.manufacturerRepository.deleteById(id);
    }


    @GetMapping("/{id}")
    public Optional<Manufacturer> selectedManufacturer(@PathVariable Long id) {
        return this.manufacturerRepository.findById(id);
    }


}
